package mateourrutia.Imp;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.Currency.AllCurrency;
import mateourrutia.Domain.Currency.CurrencyInterface;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.persistance.FileWriter;
import mateourrutia.utils.persistance.Writers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Las implementaciones de los metodos del DAO son
 * iguales indistintamente de su implementacion.
 *
 * La idea es que cualquier clase pueda ser almacenada
 * como String o como Byte de manera independiente.
 */
public abstract class TransactionHistoryImp<T extends Writers<TransactionHistory>> implements TransactionHistoryDAO {
    T Writer = getTransactionHistoryImp();

    protected abstract T getTransactionHistoryImp();

    @Override
    public String getFilePath() {
        return Writer.getFilePath();
    }

    @Override
    public void create(TransactionHistory objects) {
        Writer.create(objects);
    }

    @Override
    public void add(TransactionHistory objects) throws ObjectAlreadyExistsException {
        Writer.add(objects);
    }

    @Override
    public TransactionHistory get(UUID uuid) throws ObjectNotFoundException {
        return Writer.get(uuid);
    }

    @Override
    public boolean update(TransactionHistory objects) throws ObjectNotFoundException, OperationFailedException {
        return Writer.update(objects);
    }

    @Override
    public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
        return Writer.delete(uuid);
    }

    @Override
    public List<TransactionHistory> getAll() {
        return Writer.getAll();
    }

    @Override
    public List<TransactionHistory> getAll(
            TransactionHistory.Status   status,
            TransactionHistory.Type     type,
            CurrencyInterface           currency,
            Long                        cbu,
            Double                      minBalance,
            Double                      maxBalance
    ) {
        List<TransactionHistory> list = Writer.getAll();

        return list.stream()
                .filter(transaction -> (cbu == null || cbu == 0 ||
                        transaction.getFromAccount().getCbu().equals(cbu) ||
                        (transaction.getToAccount() != null &&
                                transaction.getToAccount().getCbu().equals(cbu))))
                .filter(transaction -> (status == TransactionHistory.Status.ALL ||
                        transaction.getStatus() == status))
                .filter(transaction -> (type == TransactionHistory.Type.ALL ||
                        transaction.getType() == type))
                .filter(transaction -> (currency == AllCurrency.ALL ||
                        transaction.getFromAccount().getCurrency() == currency))
                .filter(transaction -> (minBalance == 0 ||
                        transaction.getAmount() >= minBalance))
                .filter(transaction -> (maxBalance == 0 ||
                        transaction.getAmount() <= maxBalance))
                .sorted(Comparator.comparing(TransactionHistory::getDate))
                .collect(Collectors.toList());
    }
}
