package mateourrutia.Imp.StringWriter;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.persistance.StringWriter;

import java.util.List;
import java.util.UUID;

public class ClientString implements ClientDAO {
    StringWriter<Client> fileWriter = new StringWriter<Client>( Client.class.getSimpleName() );

    @Override
    public String getFilePath() {
        return fileWriter.getFilePath();
    }

    @Override
    public void create(Client objects) {
        fileWriter.create( objects );
    }

    @Override
    public void add(Client objects) throws ObjectAlreadyExistsException {
        fileWriter.add( objects );
    }

    @Override
    public Client get(UUID uuid) throws ObjectNotFoundException {
        return fileWriter.get( uuid );
    }

    @Override
    public boolean update(Client objects) throws ObjectNotFoundException, OperationFailedException {
        return fileWriter.update( objects );
    }

    @Override
    public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
        return fileWriter.delete( uuid );
    }

    @Override
    public List<Client> getAll() {
        return fileWriter.getAll();
    }
}
