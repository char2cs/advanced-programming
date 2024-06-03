package mateourrutia.Imp.FileWriter;
import mateourrutia.DAO.ClientDAO;
import mateourrutia.Imp.AccountImp;

public class AccountFile extends AccountImp {
    @Override
    protected ClientDAO getClientImp() {
        return new ClientFile();
    }
}