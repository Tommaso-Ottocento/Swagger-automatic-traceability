package io.swagger.api;

import io.swagger.model.Percorso;
import io.swagger.model.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.lang.Object;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-22T07:26:37.397Z")

@Controller
public class FSMApiController implements FSMApi {
	
	// Variabili globali
    static Stato stPresente[]=new Stato [5];    //Array di Stati che salva lo stato in cui si trova una determinata provetta
    static ArrayList<Stato> stati = new ArrayList<Stato>();
    static ArrayList<Transizione> transizioni = new ArrayList<Transizione>();
    public static String percorso="";
    static int minute1[]=new int[] {0,0,0,0,0}; // Array di interi che memorizza i minuti del passaggio da s1 a s2
    public static int i;   // importantissima variabile che fa da indice per tutti gli array; se i==1 allora si tratta della provetta 1
    public static int min3[]=new int [] {0,0,0,0,0}; //Array di interi che memorizza i minuti del passaggio tra s3 e s4
    static Stato stPassato[]=new Stato [5]; //Ricorda lo stato precedente, serve per la stampa
    public static int countOfTimer=0; //contatore per eseguire il Timer una sola volta
    public static Boolean oneTimer[]=new Boolean[] {true, true, true, true, true};   //Array di Boolean che serve per verificare che il Timer venga attivato solo ed esclusivamente al primo passaggio per lo stato
    public static Boolean twoTimer[]=new Boolean[] {true, true, true, true, true};   //Array di Boolean che serve per verificare che il Timer venga attivato solo ed esclusivamente al primo passaggio per lo stato	
    public static Boolean inizializzata[]=new Boolean[] {false,false,false,false,false};
    static String messaggioprimotry= new String ("Error in input");
    //
	


    private static final Logger log = LoggerFactory.getLogger(FSMApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FSMApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<State>> compute(@ApiParam(value = "give in input PI or PC or -",required=true) @PathVariable("loc") String loc,@ApiParam(value = "Identification code from 0 to 4",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        
        /**
         * compute function starts here
         * @param loc
         * @param id
         * two Strings in input, this function return a ResponseEntity<String>
         * For this FSM you have to import Stato Evento Transizione
         * */

            
            // initialization of a new event evnt (look at menu DMSF)
    	
        Evento evnt= new Evento("","", new GregorianCalendar());
    	int verifica=Integer.parseInt(id);
    	evnt.codice=id;
    	evnt.luogo=loc;
    	int intid=Integer.parseInt(id);
    	if (verifica<5){
    		principale(evnt,inizializzata);
    		inizializzata[verifica]=true;
    	}
    		
    		/**Check if id is minor of five and if the FSM corresponding of verifica number is initialized
    	 	*We pass to principale function
    	 	*/ 
    	
    	/**
    	 * Creation of List<State> with the name percorso and the id
    	 * It's the same for percorso but it is useless it is only an example
    	 * */
    	 List<Percorso> percorsi = new ArrayList<>();	
    	 List<State> stati=new ArrayList<>();
    	 State st= new State ();;
    	 st.setName(percorso);
    	 st.setId(Long.parseLong(id));
         List<String> stringhe= new ArrayList<>();
         
    	 if(intid>4){
             System.out.println("1");
			System.out.println(percorso);
			System.out.println();
			percorsi.add(new Percorso("Error in input, wrong id"));
			stringhe.add(new String("Error in input wrong id S"));
			State msg_state= new State ();;
	    	msg_state.setName(messaggioprimotry);
	    	msg_state.setId(Long.parseLong(id));
	    	stati.add(msg_state);
			
			return new ResponseEntity<List<State>>(stati, HttpStatus.BAD_REQUEST);
    	 }
        if (accept != null && accept.contains("")) {
        	percorsi.add(new Percorso(percorso));
        	stringhe.add(percorso);
        	System.out.println("2");
     		System.out.println(percorso);
     		System.out.println();
     		stati.add(st);
     		

            
     		return new ResponseEntity<List<State>>(stati, HttpStatus.OK);
        }

        else{
        	percorsi.add(new Percorso("Why am I here?"));
        	return new ResponseEntity<List<State>>(HttpStatus.NOT_IMPLEMENTED);
        	}
   
    /**
    * This function uses the event evnt and generate a transition in FSM
    @param Evento evnt
    @param inzializzata
    * This function calls createDefaultMSF if inizializzata[i] is true, therefore if it is the first time that a tube test code is used a defaultFSM is created
    * This function memorizes the time of the event and modifies the global variables with the purpose of calculate the difference of time
    * calls runTranzioniStatoPresente
    */
    }
    public void principale (Evento evnt, Boolean [] inizializzata)  {
    	int verifica=Integer.parseInt(evnt.getCodice());
		i=verifica;		
		if(!(inizializzata[verifica]==true)){

		creaDefaultMSF();
		buildPath("",stPresente[verifica], true);
		printPath();
		
		}
		if(verifica<6){
			evnt.codice="0";
		}
		stPassato[i]=stPresente[i];
		
		if(stPresente[i].isIniziale()) {
			buildPath("",stPresente[i], true);
		}
		
		if(stPresente[i].getNome().equals("s1")){
			Calendar temexp=evnt.getCalendar();
			minute1[i] = temexp.get(Calendar.MINUTE);
			System.out.println("Tempo FIRST IF min1  "+minute1[i]);

			
		}
	//Se sono su s1 prendo il tempo e lo metto in minute1 per la differenza di tempo con l'istante dell'evento in s2
	if(stPresente[i].getNome().equals("s3")){
			Calendar temexp=evnt.getCalendar();
			min3[i] = temexp.get(Calendar.MINUTE);
			System.out.println("Tempo THIRD IF sec3  "+min3[i]);
		}

	switch(runTransizioniStatoPresente(evnt,minute1[i],min3[i])) {
	case 0:
		break;
	case 1:
		System.out.println("[Errore condizione non trovata]");
		break;
	case -1:
		System.out.println("Dead end");
		break;
	}
	//
    }
    
    //RUN TRANSIZIONE
    
	/**
	 * this method is used for transitions and moreover it controls the condition in input from the user with the transitions of createDefaultMSF
	 *
	 * @param c condition of transition for the movements
	 * @return 1 if the number of condition doesn't exist in transition or in the FSM
	 *        -1 if the state has no exit transition
	 *        0 if there is and 'end of program' character
	 */
    
    public static int runTransizioniStatoPresente(Evento evnt, int minute1, int min3) {
		boolean TRAN_FLAG = false;
		//int minute1;
		transizioni = stPresente[i].getTransizioni();
		if(transizioni.size() >= 1) {
			for(Transizione t:transizioni) {
				//if(c == t.getCondizione()) {
				Calendar tempo=new GregorianCalendar();
				tempo=GregorianCalendar.getInstance();
				int minute = tempo.get(Calendar.MINUTE);
				System.out.println("Minuti esatti transizione "+minute);

				if(stPresente[i].getNome().equals("s2")){

						
					//Calendar temexp=exp.getCalendar();
					//int minute1 = temexp.get(Calendar.MINUTE);
					System.out.println("Difference of time in minutes  "+(minute-minute1));
					if(minute-minute1<=2){
						evnt.codice="0";
					}
					if(minute-minute1==2 && evnt.getLuogo().equals("PI")){
						evnt.codice="2";
					}
					if(minute-minute1>2 && (evnt.getLuogo().equals("PC")||evnt.getLuogo().equals("PI"))){
						evnt.codice="0";
					}
					if(minute-minute1>2 && evnt.getLuogo().equals("-")){
						System.out.println("SEGNALE!");
						evnt.luogo="PI";
						evnt.codice="0";
					}
						
				}

			
				if(stPresente[i].getNome().equals("s4")){

						
					//Calendar temexp=exp.getCalendar();
					//int minute1 = temexp.get(Calendar.MINUTE);
					System.out.println("Time DIF minutes  "+(minute-min3));
					if(minute-min3<=3){
						evnt.codice="0";
					}
					if(minute-min3==3 && evnt.getLuogo().equals("PC")){
						evnt.codice="30";
					}
					if(minute-min3>3 && (evnt.getLuogo().equals("PC")||evnt.getLuogo().equals("PI"))){
						evnt.codice="0";
					}
					if(minute-min3>3 && evnt.getLuogo().equals("-")){
						System.out.println("SEGNALE!");
						evnt.luogo="PC";
						evnt.codice="0";
					}
						
				}
				if(evnt.equals(t.getCondizione())) {
					buildPath(t.getAzione(), t.getDestinazione(), false);
					stPresente[i] = t.getDestinazione();

					if(stPresente[i].getNome().equals("s2") && oneTimer[i]==true){	
					final int sec=1000;
					final int nProv=i;
					final Timer timer=new Timer();
					TimerTask tt=new TimerTask(){
						public void run(){
							System.out.println();
							System.out.println("PAY ATTENTION");
							System.out.println("Signal in the second state");
							countOfTimer++;
							if(countOfTimer>=1){
								System.out.println("ERROR IN OBJECT NUMBER "+nProv);
								timer.cancel();
							}
		
						}	
					};
					timer.scheduleAtFixedRate(tt,sec*120, sec*10);
					oneTimer[i]=false;
					}
					countOfTimer=0;
					if(stPresente[i].getNome().equals("s4") && twoTimer[i]==true){	
					final int sec=1000;
					final int nProv=i;
					final Timer timer=new Timer();
					TimerTask tt=new TimerTask(){
						public void run(){
							System.out.println();
							System.out.println("PAY ATTENTION");
							System.out.println("Signal in the fourth state");
							countOfTimer++;
							if(countOfTimer>=1){
								System.out.println("ERROR IN OBJECT NUMBER "+nProv);
								timer.cancel();
							}
		
						}	
					};
					timer.scheduleAtFixedRate(tt,sec*180, sec*10);
					twoTimer[i]=false;
					}

				/** two minutes timer in s2
				* This times starts to count 2 minutes when a object (test tube) enters in s2
				* oneTimer array is an array of Boolean with the purpose of control that the timer is used one time for each id in the right state
				* countOfTimer ends the timer at the first iteration 
				*/
					
				/** three minutes timer in s4
				* This times starts to count 3 minutes when a object (test tube) enters in s4
				* oneTimer array is an array of Boolean with the purpose of control that the timer is used one time for each id in the right state
				* countOfTimer ends the timer at the first iteration 
				*/

					
					//PROVA PRINT
					printPath();
					TRAN_FLAG=true;
				}
			}
			if(!TRAN_FLAG) {
				return 1;
			}
		}else {
			return -1;
		}
		return 0;
	}
    
	/**
	 * Creation of a default FSM
	 */
    
	private static void creaDefaultMSF() {
		
		stati = new ArrayList<Stato>();
		transizioni = new ArrayList<Transizione>();
		
		Stato s1 = new Stato("s1", true);
		Stato s3 = new Stato("s3");
		Stato s2 = new Stato("s2");
		Stato e = new Stato("e");
		Stato s4 = new Stato("s4");
		Stato ok = new Stato("ok");
		
		stati.add(s3);
		stati.add(s1);
		stati.add(s2);
		stati.add(e);
		stati.add(s4);
		stati.add(ok);
		
		s1.addTransizione(new Transizione(s2, new Evento ("0","PI"), "Cambio nel PI"));
		s1.addTransizione(new Transizione(s1, new Evento ("0","-"), "Resto nello stato"));
		s1.addTransizione(new Transizione(e, new Evento("0","PC"), "Errore luogo exit"));
		s2.addTransizione(new Transizione(e, new Evento("0","PC"), "Errore luogo exit"));
		s2.addTransizione(new Transizione(s3, new Evento("2","PI"), "tempo esatto per il PI"));
		s2.addTransizione(new Transizione(s2, new Evento("0","-"), "Resto per altro tempo"));
		s2.addTransizione(new Transizione(e, new Evento("0","PI"), "Errore tempo exit"));
		s3.addTransizione(new Transizione(e, new Evento("0","PI"), "Errore luogo exit"));
		s3.addTransizione(new Transizione(s4, new Evento("0","PC"), "Passo proseguire"));
		s3.addTransizione(new Transizione(s3, new Evento("0","-"), "Resto nello stato"));
		s4.addTransizione(new Transizione(ok, new Evento("30","PC"), "OK"));
		s4.addTransizione(new Transizione(e, new Evento("0","PI"), "Errore luogo exit"));
		s4.addTransizione(new Transizione(s4, new Evento("0","-"), "Resto per altro tempo"));
		s4.addTransizione(new Transizione(e, new Evento("0","PC"), "Errore luogo exit"));
		
		stPresente[i] = s1;
	}

	/**
	 * Construction of a graphic representation of the final state machine 
	 * If the state is initial (.isIniziale) this function prints the first state
	 *
	 * @param a String of Transition
	 * @param d new state name after the transition
	 * @param f is a Boolean useful for understanding if the state is initial (.isIniziale)
	 */
	
	public static void buildPath(String a, Stato d, boolean f) {
		
		if(f) {
			percorso = "|"+stPresente[i].getNome()+"|"; 
		}else {
			percorso = "||"+stPassato[i].getNome()+"| "+ "-- "+a+"--> "+"|"+d.getNome()+"|";
		}		
	}
	
	/**
	 * Print in output of the graphic representation of 'percorso'
	 */
	
	public static void printPath() {
		System.out.println();
		System.out.println(percorso);
		System.out.println();
	}
} 

