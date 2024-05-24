package mateourrutia;

import mateourrutia.DAO.imp.PabellonDAOImp;
import mateourrutia.domain.Pabellon;

public class Main {
	public static void main(String[] args) {
		PabellonDAOImp pabellonDAOImp = new PabellonDAOImp();

		pabellonDAOImp.create(new Pabellon(
				1,
				"Testing",
				"USAL"
		));

		for (Pabellon pabellon : pabellonDAOImp.getAll())
			System.out.println(pabellon);
	}
}