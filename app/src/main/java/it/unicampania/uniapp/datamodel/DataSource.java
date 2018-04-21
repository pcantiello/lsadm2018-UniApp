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

    // Unica instanza
    private static DataSource instance = null;

    // Costruttore privato
    private DataSource() {
        elencoStudenti = new Hashtable<>();
        popolaDataSource();
    }

    /**
     * Ottiene il riferimento alla sorgente dati
     * @return restituisce l'instanza corrente
     */
    public static DataSource getInstance() {
        if (instance == null)
            instance = new DataSource();
        return instance;
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

    // Popolo il data source con dati di prova
    private void popolaDataSource() {
        elencoStudenti.put("A13001257", new Studente("A13001257", "Esposito", "Gennaro", 28, 26.5));
        elencoStudenti.put("A13000876", new Studente("A13000876", "Rossi", "Mario", 16, 22.3));
        elencoStudenti.put("A14001234", new Studente("A13001234", "Bianchi", "Luca", 38, 28.6));
        elencoStudenti.put("A13008764", new Studente("A13008764", "Smith", "John", 90, 27.5));
    }

}
