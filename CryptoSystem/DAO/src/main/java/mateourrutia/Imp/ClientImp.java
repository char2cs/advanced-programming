package mateourrutia.Imp;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.persistance.FileWriter;
import mateourrutia.utils.persistance.Writers;

import java.util.List;
import java.util.UUID;

public abstract class ClientImp<T extends Writers<Client>> implements ClientDAO {
    protected final T Writer = getClientImp();

    protected abstract T getClientImp();

    @Override
    public String getFilePath() {
        return Writer.getFilePath();
    }

    @Override
    public void create(Client objects) {
        Writer.create( objects );
    }

    @Override
    public void add(Client objects) throws ObjectAlreadyExistsException {
        Writer.add( objects );
    }

    @Override
    public Client get(UUID uuid) throws ObjectNotFoundException {
        return Writer.get( uuid );
    }

    @Override
    public boolean update(Client objects) throws ObjectNotFoundException, OperationFailedException {
        return Writer.update( objects );
    }

    @Override
    public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
        return Writer.delete( uuid );
    }

    @Override
    public List<Client> getAll() {
        return Writer.getAll();
    }
}
