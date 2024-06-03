package mateourrutia.Imp;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.persistance.FileWriter;
import mateourrutia.utils.persistance.Writers;

import java.util.List;
import java.util.UUID;

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

}
