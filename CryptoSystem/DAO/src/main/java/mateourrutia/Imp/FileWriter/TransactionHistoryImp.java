package mateourrutia.Imp.FileWriter;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.ListWriter;

import java.util.List;
import java.util.UUID;

public class TransactionHistoryImp implements TransactionHistoryDAO {

    ListWriter<TransactionHistory> listWriter = new ListWriter<TransactionHistory>( TransactionHistory.class.getSimpleName() );

    @Override
    public void create(TransactionHistory objects) {
        listWriter.create(objects);
    }

    @Override
    public void add(TransactionHistory objects) throws ObjectAlreadyExistsException {
        listWriter.add(objects);
    }

    @Override
    public TransactionHistory get(UUID uuid) throws ObjectNotFoundException {
        return listWriter.get(uuid);
    }

    @Override
    public boolean update(TransactionHistory objects) throws ObjectNotFoundException, OperationFailedException {
        return listWriter.update(objects);
    }

    @Override
    public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
        return listWriter.delete(uuid);
    }

    @Override
    public List<TransactionHistory> getAll() {
        return listWriter.getAll();
    }

}
