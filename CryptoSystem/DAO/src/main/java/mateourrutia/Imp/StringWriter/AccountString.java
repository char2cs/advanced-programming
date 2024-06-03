package mateourrutia.Imp.StringWriter;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Imp.AccountImp;

public class AccountString extends AccountImp {
    @Override
    protected ClientDAO getClientImp() {
        return new ClientString();
    }
}
