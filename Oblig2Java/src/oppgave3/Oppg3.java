package oppgave3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Oppg3 {

	public static void main(String[] args) {
		
		List<Ansatt> ansatte = new ArrayList<Ansatt>();
		List<String> ansattNavn = new ArrayList<String>();
		
		Ansatt ansatt1 = new Ansatt("Ansatt1", "A1", Kjonn.KVINNE, "CEO - sjef", 1_000_000);
		Ansatt ansatt2 = new Ansatt("Ansatt2", "A2", Kjonn.KVINNE, "Manager - sjef", 600_000);
		Ansatt ansatt3 = new Ansatt("Ansatt3", "A3", Kjonn.MANN, "Manager - sjef", 600_000);
		Ansatt ansatt4 = new Ansatt("Ansatt4", "A4", Kjonn.MANN, "Clerk", 300_000);
		Ansatt ansatt5 = new Ansatt("Ansatt5", "A5", Kjonn.KVINNE, "Clerk", 300_000);
		
		ansatte.add(ansatt1);
		ansatte.add(ansatt2);
		ansatte.add(ansatt3);
		ansatte.add(ansatt4);
		ansatte.add(ansatt5);
		
		// a
		ansatte.parallelStream().forEach( (a) -> ansattNavn.add( a.getFornavn() ) );
		System.out.println("Liste med ansatte navn: " + ansattNavn.toString() + "\n");
		
		// b
		AtomicInteger antallKvinner = new AtomicInteger();
		ansatte.forEach( (a) -> {
			if(a.getKjonn() == Kjonn.KVINNE) {
				antallKvinner.getAndIncrement();
			}
		});
		System.out.println("Antall kvinner: " + antallKvinner.get() + "\n");
		
		//c
		AtomicInteger total = new AtomicInteger();
		List<Ansatt> kvinner = ansatte.parallelStream().filter((a) -> a.getKjonn() == Kjonn.KVINNE).collect(Collectors.toList());
		kvinner.parallelStream().forEach((a) -> total.addAndGet(a.getAarslonn()));
		System.out.println("Gjennomsnittslønn kvinner: " + total.get() / kvinner.size() + "\n");
		
		//d
		ansatte.parallelStream().filter((a) -> a.getStilling().toLowerCase().contains("sjef")).forEach((a) -> a.setAarslonn((int)(a.getAarslonn() * 1.07)));
		System.out.println("Ansatte etter sjefers lønnsøkning: \n" + ansatte.toString() + "\n");
		
		//e	
		System.out.println("Tjener noen ansatte over 800 000?: " + ansatte.parallelStream().anyMatch((a) -> a.getAarslonn() > 800_000) + "\n");
		
		//f
		System.out.print("Alle ansatte: ");
		ansatte.parallelStream().forEach((a) -> System.out.print(a.toString()));
		
		//g
		AtomicInteger lavestLonn = new AtomicInteger(ansatte.get(0).getAarslonn());
		ansatte.parallelStream().forEach((a) -> {
			if(a.getAarslonn() < lavestLonn.get()) {
				lavestLonn.set(a.getAarslonn());
			}
		});
		System.out.println("\n\nLavest Lønn: " + lavestLonn.get() + "\nAnsatte med lavest lønn:\n" + 
		ansatte.parallelStream().filter((a) -> a.getAarslonn() <= lavestLonn.get()).collect(Collectors.toList()).toString());
		
		//h
		System.out.println("\nSum av tall fra 1-1000 som er delelig på 3 eller 5: \n" + 
		IntStream.range(1, 1001).filter((i) -> erDeleligPaa(i,3) || erDeleligPaa(i,5)).sum());
		
	}
	
	public static boolean erDeleligPaa(int input, int delerPaa) {
		int sjekk = input / delerPaa;
		return sjekk == (double)input / delerPaa;
	}

}


