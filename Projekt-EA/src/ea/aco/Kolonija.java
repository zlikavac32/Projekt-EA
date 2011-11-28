package ea.aco;

public abstract class Kolonija {
	
	protected Mrav[] mravi;
	
	protected Mrav najbolje;
	
	public abstract void inicijaliziraj();
	
	public abstract void obaviSetnje();
	
	public abstract void azurirajTragove();
	
	public abstract void azurirajTragove(int brojNajboljihMrava);
	
	public abstract void obaviIsparavnje();
	
	public Mrav vratiLokalnoNajgore() {
		if (mravi == null || mravi.length < 1) { return null; }
		Mrav najgore = mravi[0];
		for (int i = 1; i < mravi.length; i++) {
			if (mravi[i].compareTo(najgore) > 0) { najgore = mravi[i]; }
		}
		return najgore.kopiraj();
	}

	public Mrav vratiLokalnoNajbolje() {
		if (mravi == null || mravi.length < 1) { return null; }
		Mrav najbolje = mravi[0];
		for (int i = 1; i < mravi.length; i++) {
			if (mravi[i].compareTo(najbolje) < 0) { najbolje = mravi[i]; }
		}
		return najbolje.kopiraj();
	}
	
	protected void obnoviGlobalnoNajbolje() {
		Mrav moguceNajbolje = vratiLokalnoNajbolje();
		if (najbolje == null || moguceNajbolje.compareTo(najbolje) < 0) { najbolje = moguceNajbolje; }
	}
	
	public Mrav vratiGlobalnoNajbolje() { return najbolje.kopiraj(); }

	public abstract void evoluiraj(int brojMravaAzurira);

	public abstract void evoluiraj();
}
