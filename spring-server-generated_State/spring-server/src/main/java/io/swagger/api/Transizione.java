package io.swagger.api;
/**
 * 
 * @author Giulia Pegoraro
 * @version 0.1
 *
 */
public class Transizione {
	
	private Evento condizione;
	private String azione;
	Stato destinazione;
	
	/**
	 * Costruttore che specifica lo stato di destinazione
	 * @param destinazione
	 */
	Transizione(Stato destinazione){
		this.condizione =new Evento("");
		this.azione = "";
		this.destinazione = destinazione;
	}
	/**
	 * Costruttore che specifica lo stato di destinazione, la condizione della transizione e l' azione della transizione
	 * 
	 * @param destinazione
	 * @param condizione
	 * @param azione
	 */
	Transizione(Stato destinazione, Evento condizione, String azione){
		this.condizione = condizione;
		this.azione = azione;
		this.destinazione = destinazione;
	}
	/**
	 * Il metodo ritorna la condizione della transizione
	 * 
	 * @return condizione
	 */
	public Evento getCondizione() {
		return condizione;
	}
	/**
	 * Il metodo ritorna l'azione della transizione
	 * 
	 * @return azione della transizione
	 */
	public String getAzione() {
		return azione;
	}
	/**
	 * Il metodo ritorna la destinazione della transizione
	 * 
	 * @return stato di destinazione
	 */
	public Stato getDestinazione() {
		return destinazione;
	}
	/**
	 * Il metodo imposta la condizione della transizione con un nuovo valore
	 * 
	 * @param condizione nuova condizione da impostare
	 */
	public void setCondizione(Evento condizione) {
		this.condizione = condizione;
	}
	/**
	 * Il metodo imposta l'azione della transizione
	 * 
	 * @param azione nuova azione da impostare
	 */
	public void setAzione(String azione) {
		this.azione = azione;
	}
	/**
	 * Il metodo imposta lo stato di destinazione della transizione con un nuovo valore 
	 * 
	 * @param destinazione stato da impostare come destinazione
	 */
	public void setDestinazione(Stato destinazione) {
		this.destinazione = destinazione;
	}
	
}