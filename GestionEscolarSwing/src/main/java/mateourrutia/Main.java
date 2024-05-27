package mateourrutia;

import mateourrutia.DAO.imp.PabellonDAOImp;
import mateourrutia.domain.Pabellon;

public class Main {
	public static void main(String[] args) {
		PabellonDAOImp pabellonDAOImp = new PabellonDAOImp();

		pabellonDAOImp.create(new Pabellon(
				2,
				"Testing2",
				"USAL2"
		));

		for (Pabellon pabellon : pabellonDAOImp.getAll())
			System.out.println(pabellon);
	}
}