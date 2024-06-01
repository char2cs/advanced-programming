package mateourrutia.Imp.FileWriter;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.ListWriter;

import java.util.List;
import java.util.UUID;

public class ClientImp implements ClientDAO {
    ListWriter<Client> listWriter = new ListWriter<Client>( Client.class.getSimpleName() );

    @Override
    public void create(Client objects) {
        listWriter.create( objects );
    }

    @Override
    public void add(Client objects) throws ObjectAlreadyExistsException {
        listWriter.add( objects );
    }

    @Override
    public Client get(UUID uuid) throws ObjectNotFoundException {
        return listWriter.get( uuid );
    }

    @Override
    public boolean update(Client objects) throws ObjectNotFoundException, OperationFailedException {
        return listWriter.update( objects );
    }

    @Override
    public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
        return listWriter.delete( uuid );
    }

    @Override
    public List<Client> getAll() {
        return listWriter.getAll();
    }
}
