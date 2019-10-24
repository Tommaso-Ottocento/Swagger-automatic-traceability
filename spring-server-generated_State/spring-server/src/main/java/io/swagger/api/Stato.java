package io.swagger.api;
import java.util.ArrayList;
/**
*@author Giulia Pegoraro
*@version 0.1
*
*/
public class Stato {
	
	private boolean isIniziale;
	private String nome;
	private ArrayList<Transizione> tr;
	/**
	 * Costruttore che specifica il nome dello stato
	 * 
	 * @param nome rappresenta il nome dello stato
	 */
	Stato(String nome){
		this.isIniziale = false;
		this.nome = nome;
		this.tr = new ArrayList<Transizione>();
	}
	/**
	 * Costruttore che specifica le transizioni uscenti
	 * 
	 * @param nome rappresenta il nome dello stato
	 * @param tr e' un vettore di transizioni uscenti
	 */
	Stato(String nome, ArrayList<Transizione> tr){
		this.isIniziale = false;
		this.nome = nome;
		this.tr = tr;
	}
	/**
	 * Costruttore che specifica il nome dello stato, se e' uno stato iniziale e le transizioni uscenti
	 * 
	 * @param nome rappresenta il nome dello stato
	 * @param isIniziale variabile booleana che indica se e' uno stato iniziale
	 * @param tr e' un vettore di transizioni uscenti
	 */
	Stato(String nome, boolean isIniziale, ArrayList<Transizione> tr){
		this.isIniziale = isIniziale;
		this.nome = nome;
		this.tr = tr;
	}
	/**
	 * Costruttore che specifica il nome dello stato e se e' uno stato iniziale
	 * 
	 * @param nome rappresenta il nome dello stato
	 * @param isIniziale variabile booleana che indica se e' uno stato iniziale
	 */
	Stato(String nome, boolean isIniziale){
		this.isIniziale = isIniziale;
		this.nome = nome;
		this.tr = new ArrayList<Transizione>();
	}
	/**
	 * Metodo che ritorna se lo stato e' iniziale o meno
	 * 
	 * @return
	 */
	public boolean isIniziale() {
		return isIniziale;
	}
	/**
	* Metodo che setta se lo stato è iniziale
	*
	* @parametro un booleano
	*/
	public void setIsIniziale(boolean isIniziale) {
		this.isIniziale = isIniziale;
	}
	/**
	 * Il metodo ritorna il nome dello stato
	 * 
	 * @return il nome dello stato
	 */
	
	public String getNome() {
		return nome;
	}
	/**
	 * Il metodo ritorna l'oggetto stato
	 * 
	 * @return l'oggetto stato
	 */
	
	public Stato getStato() {
		return this;
	}
	/**
	 * Il metodo imposta il nome con il valore parametro inserito
	 *  
	 * @param nome nome dello stato da impostare
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Il metodo ritorna tutte le transizioni uscenti dallo stato
	 * 
	 * @return ArrayList di transizioni
	 */
	public ArrayList<Transizione> getTransizioni() {
		return this.tr;
	}
	/**
	 * Il metodo aggiunge transizioni al vettore
	 * 
	 * @param tr transizione da inserire
	 */
	public void addTransizione(Transizione tr) {
		this.tr.add(tr);
	}
}