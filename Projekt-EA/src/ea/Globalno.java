package ea;

public class Globalno {

	private static volatile int brzina;
	
	private static volatile boolean zaustavljeno = true;
	
	public static void postaviBrzinu(int brzina) { Globalno.brzina = brzina; }
	
	public static int vratiBrzinu() { return brzina; }
	
	public static void postaviZaustavljeno(boolean zaustavljeno) { Globalno.zaustavljeno = zaustavljeno; }
	
	public static boolean jeZaustavljen() { return zaustavljeno; }
	
}
