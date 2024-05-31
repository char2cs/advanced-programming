package mateourrutia.Imp.FileWriter;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.DeleteFailedException;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.UpdateFailedException;
import mateourrutia.helper.FileWriter.FileWriter;

import java.util.List;

public class TransactionHistoryImp implements TransactionHistoryDAO {

    FileWriter<TransactionHistory> fileWriter = new FileWriter<TransactionHistory>( TransactionHistory.class.getSimpleName() );

    @Override
    public void create(TransactionHistory objects) {
        fileWriter.create(objects);
    }

    @Override
    public void add(TransactionHistory objects) throws ObjectAlreadyExistsException {
        fileWriter.add(objects);
    }

    @Override
    public TransactionHistory get(Integer uuid) throws ObjectNotFoundException {
        return fileWriter.get(uuid);
    }

    @Override
    public boolean update(TransactionHistory objects) throws ObjectNotFoundException, UpdateFailedException {
        return fileWriter.update(objects);
    }

    @Override
    public boolean delete(Integer uuid) throws ObjectNotFoundException, DeleteFailedException {
        return fileWriter.delete(uuid);
    }

    @Override
    public List<TransactionHistory> getAll() {
        return fileWriter.getAll();
    }

}
