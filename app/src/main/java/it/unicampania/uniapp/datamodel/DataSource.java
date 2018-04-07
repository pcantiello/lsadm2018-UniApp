package it.unicampania.uniapp.datamodel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Classe che simula la sorgente dei dati
 */
public class DataSource {

    // Lista locale per simulare una ipotetica sorgente dati
    private Hashtable<String, Studente> elencoStudenti;

    public DataSource() {
        elencoStudenti = new Hashtable<>();
    }

    /**
     * Aggiunge un studente
     * @param studente studente da aggiungere
     */
    public void addStudente(Studente studente) {
        elencoStudenti.put(studente.getMatricola(), studente);
    }

    /**
     * Elimina uno studente
     * @param matricola matricola da eliminare
     */
    public void deleteStudente(String matricola) {
        elencoStudenti.remove(matricola);
    }

    /**
     * Cerca uno studente partendo dalla matricola
     * @param matricola matricola da cercare
     * @return Studente trovoto (null in caso contrario)
     */
    public Studente getStudente(String matricola) {
        return elencoStudenti.get(matricola);
    }

    /**
     * Restituisce un elenco di studenti che hanno la matricola con il prefisso passato
     * @param prefissoMatricola prefisso da cercare
     * @return elenco studenti trovato
     */
    public List<Studente> getElencoStudenti(String prefissoMatricola) {

        ArrayList<Studente> risultato = new ArrayList<Studente>();

        // Itero tutti gli elementi per cercare quelli che soddisfano il requisito richiesto
        for (Map.Entry<String, Studente> elemento: elencoStudenti.entrySet()) {
            if (elemento.getKey().startsWith(prefissoMatricola))
                risultato.add(elemento.getValue());
        }
        return risultato;
    }

}
