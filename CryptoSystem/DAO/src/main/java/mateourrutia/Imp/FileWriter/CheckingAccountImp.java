package mateourrutia.Imp.FileWriter;

import mateourrutia.DAO.CheckingAccountDAO;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.DeleteFailedException;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.UpdateFailedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CheckingAccountImp implements CheckingAccountDAO {
    ClientImp clientImp = new ClientImp();

    @Override
    public void create(CheckingAccount objects, Client client) throws ObjectNotFoundException, UpdateFailedException {
        Client getClient = clientImp.get( client.getUuid() );

        getClient.getCheckingAccounts()
                .add( objects );

        clientImp.update(getClient);
    }

    @Override
    public void add(CheckingAccount objects, Client client) throws ObjectNotFoundException, ObjectAlreadyExistsException, UpdateFailedException {
        Client getClient = clientImp.get( client.getUuid() );

        try {
            get(objects.getUuid(), client);
            throw new ObjectAlreadyExistsException("Object already exists");
        }
        catch (ObjectNotFoundException e) {
            getClient.getCheckingAccounts()
                    .add( objects );

            clientImp.update(getClient);
        }
    }

    @Override
    public CheckingAccount get(Integer uuid, Client client) throws ObjectNotFoundException {
        Client getClient = clientImp.get( client.getUuid() );

        Optional<CheckingAccount> existingObject = getClient.getCheckingAccounts().stream()
                .filter( obj -> obj.getUuid().equals(uuid) )
                .findFirst();

        if ( !existingObject.isPresent() )
            throw new ObjectNotFoundException( "Object Not Found in Parent list: " + uuid );

        return existingObject.get();
    }

    @Override
    public CheckingAccount get(Integer uuid) throws ObjectNotFoundException {
        List<Client> clients = clientImp.getAll();

        for ( Client client : clients )
            try {
                return get( uuid, client );
            }
            catch (ObjectNotFoundException ignored) {}

        throw new ObjectNotFoundException( "Object Not Found: " + uuid ); // Es redundante, pero no queda otra;
    }

    @Override
    public boolean update(CheckingAccount objects, Client client) throws ObjectNotFoundException, UpdateFailedException {
        Optional<CheckingAccount> existingObject = client.getCheckingAccounts().stream()
                .filter( obj -> obj.getUuid().equals( objects.getUuid() ) )
                .findFirst();

        // TODO USE INTSTREAM HERE.

        if ( !existingObject.isPresent() )
            throw new ObjectNotFoundException( "Object Not Found in Parent list: " + objects.getUuid() );
    }

    @Override
    public boolean update(CheckingAccount objects) throws ObjectNotFoundException, UpdateFailedException {
        return false;
    }

    @Override
    public boolean delete(Integer uuid, Client client) throws ObjectNotFoundException, DeleteFailedException {
        return false;
    }

    @Override
    public List<CheckingAccount> getAll() {
        return Collections.emptyList();
    }

    @Override
    public List<CheckingAccount> getAll(Client client) {
        return Collections.emptyList();
    }

    @Override
    public List<CheckingAccount> getAll(Integer uuid) {
        return Collections.emptyList();
    }
}
