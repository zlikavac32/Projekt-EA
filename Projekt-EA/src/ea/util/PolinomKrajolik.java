package ea.util;

public class PolinomKrajolik implements Krajolik<double[][]> {

	private double[] vrijednostiUTockama;
	
	private double[][] vrijednostiVarijabli;
	
	public PolinomKrajolik(double[][] referentniKoeficijenti, double[][] vrijednostiVarijabli) {
		vrijednostiUTockama = new double[vrijednostiVarijabli.length];
		for (int i = 0; i < vrijednostiUTockama.length; i++) {
			vrijednostiUTockama[i] = racunajVrijednost(vrijednostiVarijabli[i], referentniKoeficijenti);
		}
		this.vrijednostiVarijabli = vrijednostiVarijabli;
	}
	
	@Override
	public double racunajFaktorDobrote(double[][] koeficijenti) {
		double sumaRazlikeKvadrata = 0;
		for (int i = 0; i < vrijednostiUTockama.length; i++) {
			double temp = vrijednostiUTockama[i] - racunajVrijednost(vrijednostiVarijabli[i], koeficijenti);
			sumaRazlikeKvadrata += temp * temp;
		}
		return sumaRazlikeKvadrata;
	}

	@Override
	public boolean jeValjanaVrijednost(double[][] koeficijenti) {
		//TODO: Za sada nema provjere vjerojatnosti, mozda bi bilo dobro da ima
		return true;
	}
	
	protected double racunajVrijednost(double[] varijabla, double[][] koeficijenti) {
		double vrati = 0;
		
		for (int i = 0; i < varijabla.length; i++) {
			double temp = 1;
			for (int j = 0; j < koeficijenti[i].length; j++, temp *= varijabla[i]) {
				vrati += temp * koeficijenti[i][j];
			}
		}
		
		return vrati;
	}
	
}
