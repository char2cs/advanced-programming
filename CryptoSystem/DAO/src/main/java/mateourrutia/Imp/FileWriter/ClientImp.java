package mateourrutia.Imp.FileWriter;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.DeleteFailedException;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.UpdateFailedException;
import mateourrutia.helper.FileWriter.FileWriter;

import java.util.List;

public class ClientImp implements ClientDAO {
    FileWriter<Client> fileWriter = new FileWriter<Client>( Client.class.getSimpleName() );

    @Override
    public void create(Client objects) {
        fileWriter.create( objects );
    }

    @Override
    public void add(Client objects) throws ObjectAlreadyExistsException {
        fileWriter.add( objects );
    }

    @Override
    public Client get(Integer uuid) throws ObjectNotFoundException {
        return fileWriter.get( uuid );
    }

    @Override
    public boolean update(Client objects) throws ObjectNotFoundException, UpdateFailedException {
        return fileWriter.update( objects );
    }

    @Override
    public boolean delete(Integer uuid) throws ObjectNotFoundException, DeleteFailedException {
        return fileWriter.delete( uuid );
    }

    @Override
    public List<Client> getAll() {
        return fileWriter.getAll();
    }
}
