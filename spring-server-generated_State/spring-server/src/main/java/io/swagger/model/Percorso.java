package io.swagger.model;

import java.io.Serializable;


public class Percorso implements Serializable {
    private String percorso;

    public Percorso(String percorso) {
        this.percorso= percorso;
    }

    public Percorso() {
    }

    public String getPercorso() {
        return percorso;
    }

    public void setName(String percorso) {
        this.percorso = percorso;
    }

	public boolean equals(Object obj){
		if(obj instanceof Percorso){
			Percorso p=(Percorso) obj;
			return (percorso.equals(p.percorso));
		}
		return false;
	}
}
