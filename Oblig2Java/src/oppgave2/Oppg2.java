package oppgave2;

import java.util.ArrayList;
import java.util.List;

public class Oppg2 {

	public static void main(String[] args) {
		Ansatt ansatt1 = new Ansatt("Ansatt1", "A1", Kjonn.KVINNE, "CEO", 1_000_000);
		Ansatt ansatt2 = new Ansatt("Ansatt2", "A2", Kjonn.KVINNE, "Manager", 600_000);
		Ansatt ansatt3 = new Ansatt("Ansatt3", "A3", Kjonn.MANN, "Manager", 600_000);
		Ansatt ansatt4 = new Ansatt("Ansatt4", "A4", Kjonn.MANN, "Clerk", 300_000);
		Ansatt ansatt5 = new Ansatt("Ansatt5", "A5", Kjonn.KVINNE, "Clerk", 300_000);
		
		List<Ansatt> ansatte = new ArrayList<Ansatt>();
		
		ansatte.add(ansatt1);
		ansatte.add(ansatt2);
		ansatte.add(ansatt3);
		ansatte.add(ansatt4);
		ansatte.add(ansatt5);
		
		int prosentOkningHvisMann = 15;
		
		lonnsoppgjor(ansatte, (a) -> {
			if(a.getKjonn() == Kjonn.MANN) {
				a.setAarslonn((a.getAarslonn() * (100 + prosentOkningHvisMann) / 100));
			}
		});
		
		System.out.println(ansatte.toString());
	}
	
	private static void lonnsoppgjor(List<Ansatt> ansatte, Lonning lonning) {
		ansatte.forEach((a) -> lonning.bergenLonnsoppgjor(a));
	}
	
	interface Lonning {
		void bergenLonnsoppgjor(Ansatt ansatt);
	}

}