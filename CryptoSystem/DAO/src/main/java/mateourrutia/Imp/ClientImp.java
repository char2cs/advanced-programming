package mateourrutia.Imp;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Listed;
import mateourrutia.utils.persistance.Writers;

import java.util.List;
import java.util.UUID;

/**
 * Las implementaciones de los metodos del DAO son
 * iguales indistintamente de su implementacion.
 *
 * La idea es que cualquier clase pueda ser almacenada
 * como String o como Byte de manera independiente.
 */
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
        Writer.add(objects);
    }

    @Override
    public Client get(UUID uuid) throws ObjectNotFoundException {
        return Writer.get( uuid );
    }

    @Override
    public boolean update(Client objects) throws ObjectNotFoundException, OperationFailedException {
        Listed<Account> accounts = objects.getAccounts();

        for ( Account account : accounts.getList() )
        {
            account.setClient(objects);
            accounts.set(account);
        }

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
