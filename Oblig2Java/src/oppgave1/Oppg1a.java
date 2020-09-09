package oppgave1;

import java.util.Arrays;
import java.util.List;

public class Oppg1a {

	public static void main(String[] args) {
		List<String> listen = Arrays.asList("10", "1", "20", "110", "21", "12");
		
		listen.sort((a,b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)));
		listen.forEach((s) -> System.out.println(s));

	}

}
