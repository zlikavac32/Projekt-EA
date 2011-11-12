/**
 * 
 */
package ea.aco;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ea.util.RandomGenerator;
import ea.util.XKorakaNemaPromjeneVrijednosti;

/**
 * @author Zlikavac32
 *
 */
public class MaxMinAntSystemTSPKolonija extends AntSystemTSPKolonija {
	
	protected double tauMax;
	
	protected double tauMin;
	
	protected double najboljeDelta = 0.;

	protected XKorakaNemaPromjeneVrijednosti<Mrav> trebaResetirat;
	
	public MaxMinAntSystemTSPKolonija(
		List<Par<String, Par<Double, Double>>> gradovi, int brojMrava, double konstantaIsparavanja, 
		RandomGenerator generator, double alfa, int brojGeneracija
	) {
		super(gradovi, brojMrava, konstantaIsparavanja, generator, alfa, 0);
		trebaResetirat = new XKorakaNemaPromjeneVrijednosti<Mrav>(brojGeneracija);
	}
	
	@Override
	public void evoluiraj(int brojMravaAzurira) {
		super.evoluiraj(brojMravaAzurira);
		if (trebaResetirat.jeKraj(najbolje)) { resetirajTragove(); }
	}
	
	@Override
	public void inicijaliziraj() {
		super.inicijaliziraj();
		tauMax = 1 / (konstantaIsparavanja * pohlepnaUdaljenost);
		tauMin = tauMax / alfa;
		resetirajTragove();
	}
	
	protected void resetirajTragove() {
		for (int i = 0; i < udaljenosti.length; i++) {
			for (int j = i + 1; j < udaljenosti.length; j++) {
				//TODO: Malo to napravi refactor da se neke stvari ne inicijaliziraju vise puta
				tragovi[i][j] = tragovi[j][i] = tauMax;
			}
		}
	}

	@Override
	public void azurirajTragove(int brojNajboljihMrava) {
		Arrays.sort(mravi);
		TSPMrav mrav = (TSPMrav) mravi[0];
		Iterator<Integer> putanja = mrav.iterator();
		if (!putanja.hasNext()) { return; }
		Integer pocetak = putanja.next();
		Integer kraj = null;
		double nazivnik = mrav.duljinaPuta;
		if (Double.compare(mrav.duljinaPuta, 0) == 0) {
			nazivnik = 1e-20;
		}
		double delta = 1 / nazivnik + najboljeDelta;
		while (putanja.hasNext()) {
			kraj = putanja.next();
			tragovi[pocetak][kraj] = tragovi[kraj][pocetak] += delta;
			//Mislim da ovako ide taj tauMin
			if (tragovi[pocetak][kraj] < tauMin || tragovi[pocetak][kraj] > tauMin) { tragovi[pocetak][kraj] = tragovi[kraj][pocetak] = tauMax; }
			pocetak = kraj;
		}
	}
	
	@Override
	protected void obnoviGlobalnoNajbolje() {
		Mrav najboljiMrav = najbolje;
		super.obnoviGlobalnoNajbolje();
		if (najbolje.compareTo(najboljiMrav) < 0) {
			tauMax = 1 / (konstantaIsparavanja * ((TSPMrav) najbolje).duljinaPuta);
			tauMin = tauMax / alfa;
			najboljeDelta = 1 / ((TSPMrav) najbolje).duljinaPuta;
		}
	}

}
