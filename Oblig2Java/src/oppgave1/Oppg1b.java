package oppgave1;

public class Oppg1b {

	public static void main(String[] args) {
		System.out.println("Sum av 12 og 13: " + beregn(12, 13, (i,n) -> i + n));
		System.out.println("Størst av -5 og 3: " + beregn(-5, 3, (i,n) -> i > n ? i : n));
		System.out.println("Avstand mellom 45 og 54: " + beregn(45, 54, (i,n) -> Math.abs(i - n)));	
	}
	
	public static int beregn(int i, int n, Funksjon f) {
		return f.funk(i, n);
	}
	
	interface Funksjon {
		int funk(int i, int n);
	}

}