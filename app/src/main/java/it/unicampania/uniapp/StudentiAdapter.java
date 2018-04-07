package it.unicampania.uniapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import it.unicampania.uniapp.datamodel.Studente;

/**
 * Adapter per l'interfacciamento tra la listview degli studenti e la fonte dati
 */
public class StudentiAdapter extends BaseAdapter {

    private Context context;
    private List<Studente> elencoStudenti;

    /**
     * Costruttore
     * @param context contesto in cui operare
     * @param elencoStudenti elenco dei dati da visualizzare
     */
    public StudentiAdapter(Context context, List<Studente> elencoStudenti) {
        this.context = context;
        this.elencoStudenti = elencoStudenti;
    }

    // Invocato per ottenere il numero di elementi nella lista
    @Override
    public int getCount() {
        return elencoStudenti.size();
    }

    // Invocato per ottenere l'iesimo elemento
    @Override
    public Object getItem(int i) {
        return elencoStudenti.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Invocato per ottenere la view della riga da visualizzare
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Nel caso il layout non sia stato iniettato, provvedo
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.riga_studente, null);

        // Ottengo gli ID correnti
        TextView vMatricola = view.findViewById(R.id.textMatricola);
        TextView vNomeCompleto = view.findViewById(R.id.textNomeCompleto);

        // Imposto i valori da visualizzare
        Studente s = elencoStudenti.get(i);
        vMatricola.setText(s.getMatricola());
        vNomeCompleto.setText(s.getCognome() + " " + s.getNome());

        // Restituisco la view alla lista
        return view;
    }

}
