package mateourrutia.Imp.FileWriter;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;

import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Listed;

import java.util.*;

public class AccountImp implements AccountDAO {
    ClientImp clientImp = new ClientImp();

    @Override
    public void create(Account objects, Client client) throws ObjectNotFoundException, OperationFailedException {
        Client getClient = clientImp.get( client.getUuid() );

        getClient.getAccounts().create( objects );

        clientImp.update(getClient);
    }

    @Override
    public void add(Account objects, Client client) throws ObjectNotFoundException, ObjectAlreadyExistsException, OperationFailedException {
        Client getClient = clientImp.get( client.getUuid() );

        getClient.getAccounts().add(objects);
        clientImp.update(getClient);
    }

    @Override
    public Account get(UUID uuid, Client client) throws ObjectNotFoundException {
        Client getClient = clientImp.get( client.getUuid() );

        return getClient.getAccounts().get(uuid);
    }

    @Override
    public Account get(UUID uuid) throws ObjectNotFoundException {
        List<Client> clients = clientImp.getAll();

        for ( Client client : clients )
            try {
                return get( uuid, client );
            }
            catch (ObjectNotFoundException ignored) {}

        throw new ObjectNotFoundException( "Object Not Found: " + uuid ); // Es redundante, pero no queda otra
    }

    @Override
    public boolean update(Account objects, Client client) throws ObjectNotFoundException, OperationFailedException {
        Client getClient = clientImp.get( client.getUuid() );

        getClient.getAccounts().set( objects );
        clientImp.update(getClient);

        return true;
    }

    @Override
    public boolean update(Account objects) throws ObjectNotFoundException, OperationFailedException {
        List<Client> clients = clientImp.getAll();

        for ( Client client : clients )
            if ( client.getAccounts().has(objects.getUuid()) )
                return update( objects, client );

        throw new ObjectNotFoundException( "Object Not Found: " + objects.getUuid() );
    }

    @Override
    public boolean delete(UUID uuid, Client client) throws ObjectNotFoundException, OperationFailedException {
        Client getClient = clientImp.get( client.getUuid() );

        if ( getClient.getAccounts().remove(uuid) )
            return clientImp.update(getClient);

        return false;
    }

    @Override
    public Listed<Account> getAll() {
        List<Client> clients = clientImp.getAll();
        Listed<Account> accounts = new Listed<>( new ArrayList<>() );

        try {
            for ( Client client : clients )
                accounts.concat( client.getAccounts() );
        }
        catch ( ObjectAlreadyExistsException e ) {
            e.printStackTrace();
            return accounts;
        }

        return accounts;
    }
}
