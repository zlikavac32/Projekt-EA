package ea.aco;

public abstract class Kolonija {
	
	protected Mrav[] mravi;
	
	protected Mrav najbolje;
	
	public abstract void inicijaliziraj();
	
	public abstract void obaviSetnje();
	
	public abstract void azurirajTragove();
	
	public abstract void azurirajTragove(int brojNajboljihMrava);
	
	public abstract void obaviIsparavnje();
	
	public Mrav vratiNajgore() {
		if (mravi == null || mravi.length < 1) { return null; }
		Mrav najgori = mravi[0];
		for (int i = 1; i < mravi.length; i++) {
			if (mravi[i].compareTo(najgori) > 0) { najgori = mravi[i]; }
		}
		return najgori.kopiraj();
	}

	public Mrav vratiNajbolje() {
		if (mravi == null || mravi.length < 1) { return null; }
		Mrav najbolji = mravi[0];
		for (int i = 1; i < mravi.length; i++) {
			if (mravi[i].compareTo(najbolji) < 0) { najbolji = mravi[i]; }
		}
		return najbolji.kopiraj();
	}
	
	protected void obnoviGlobalnoNajbolje() {
		Mrav moguceNajbolje = vratiNajbolje();
		if (najbolje == null || moguceNajbolje.compareTo(najbolje) < 0) { najbolje = moguceNajbolje; }
	}
	
	public Mrav vratiGlobalnoNajbolje() { return najbolje; }

	public abstract void evoluiraj(int brojMravaAzurira);

	public abstract void evoluiraj();
}
