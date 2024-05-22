package mateourrutia.controller.Objects;

import mateourrutia.DAO.imp.PabellonDAOImp;
import mateourrutia.controller.General.GeneralController;
import mateourrutia.domain.Pabellon;

public class PabellonController {

    // TODO!!!
    public static GeneralController<Pabellon> get() {
        PabellonDAOImp pabellonDAO = new PabellonDAOImp();

        return new GeneralController<Pabellon>(
                pabellonDAO.getAll(),
                Pabellon.class
        );
    }

}
