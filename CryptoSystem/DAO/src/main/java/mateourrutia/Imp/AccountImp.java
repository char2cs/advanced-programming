package mateourrutia.Imp;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Listed;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Las implementaciones de los metodos del DAO son
 * iguales indistintamente de su implementacion.
 *
 * La idea es que cualquier clase pueda ser almacenada
 * como String o como Byte de manera independiente.
 */
public abstract class AccountImp implements AccountDAO {
    protected final ClientDAO clientImp = getClientImp();

    protected abstract ClientDAO getClientImp();

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

        throw new ObjectNotFoundException( "Object Not Found: " + uuid );
    }

    @Override
    public Account get(Long cbu) throws ObjectNotFoundException {
        List<Client> clients = clientImp.getAll();

        /**
         * Horrible, pero es la unica forma que tenemos de buscar
         * cuentas por CBU persistiendo solo a los clientes.
         */
        for ( Client client : clients )
            for ( Account account : client.getAccounts().getList() )
                if ( account.getCbu().equals( cbu ) )
                    return account;

        throw new ObjectNotFoundException( "Account not found, CBU: " + cbu );
    }

    @Override
    public Client getClient(UUID uuid) throws ObjectNotFoundException {
        List<Client> clients = clientImp.getAll();

        for ( Client client : clients )
            try {
                get( uuid, client );
                return client;
            }
            catch (ObjectNotFoundException ignored) {}

        throw new ObjectNotFoundException( "Object Not Found: " + uuid );
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
