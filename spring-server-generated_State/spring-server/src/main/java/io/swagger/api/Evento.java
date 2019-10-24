package io.swagger.api;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.lang.Object;
import java.util.GregorianCalendar;
public class Evento {
	public String codice;
	public String luogo;
	public Calendar cal;

	Evento(String codi){
		this.codice=codi;
	}
	Evento(String codi, String luogo){
		this.codice=codi;
		this.luogo=luogo;
	}
	Evento(String codi, Calendar cal){
		this.codice=codi;
		this.cal=cal;
	}

	Evento(String codi, String luogo, Calendar cal){
		this.codice=codi;
		this.luogo=luogo;
		this.cal=cal;
	}
	public String getCodice() {
		return codice;
	}
	public String setCodice(String codi){
		this.codice=codi;
		return codice;
	}
	public String getLuogo() {
		return luogo;
	}
	public String setLuogo(String luogo){
		this.luogo=luogo;
		return luogo;
	}
	public Calendar getCalendar() {
		return cal;	
	}
	public String getCalendarS() {
		String a=(Integer.toString(cal.get(Calendar.YEAR))+"/"+Integer.toString(cal.get(Calendar.MONTH)+1)+"/"+Integer.toString(cal.get(Calendar.DAY_OF_MONTH))+" "+Integer.toString(cal.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(cal.get(Calendar.MINUTE))+":"+Integer.toString(cal.get(Calendar.SECOND)));
		return a;

	}
	public boolean equals(Object obj){
		if(obj instanceof Evento){
			Evento exp=(Evento) obj;
			return (codice.equals(exp.codice) && luogo.equals(exp.luogo));
		}
		return false;
	}
}