package mateourrutia.Imp.StringWriter;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.persistance.StringWriter;

import java.util.List;
import java.util.UUID;

public class TransactionHistoryString implements TransactionHistoryDAO {
    StringWriter<TransactionHistory> fileWriter = new StringWriter<TransactionHistory>( TransactionHistory.class.getSimpleName() );

    @Override
    public String getFilePath() {
        return fileWriter.getFilePath();
    }

    @Override
    public void create(TransactionHistory objects) {
        fileWriter.create(objects);
    }

    @Override
    public void add(TransactionHistory objects) throws ObjectAlreadyExistsException {
        fileWriter.add(objects);
    }

    @Override
    public TransactionHistory get(UUID uuid) throws ObjectNotFoundException {
        return fileWriter.get(uuid);
    }

    @Override
    public boolean update(TransactionHistory objects) throws ObjectNotFoundException, OperationFailedException {
        return fileWriter.update(objects);
    }

    @Override
    public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
        return fileWriter.delete(uuid);
    }

    @Override
    public List<TransactionHistory> getAll() {
        return fileWriter.getAll();
    }

}
