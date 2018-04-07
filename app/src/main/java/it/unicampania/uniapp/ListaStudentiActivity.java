package it.unicampania.uniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import it.unicampania.uniapp.datamodel.DataSource;
import it.unicampania.uniapp.datamodel.Studente;

// Activity con l'elenco degli studenti
public class ListaStudentiActivity extends AppCompatActivity {

    // Riferimenti alle view
    private ListView vListaStudenti;

    // Adapter e data source
    private StudentiAdapter adapter;
    private DataSource dataSource;

    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_STUDENTE = "studente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_studenti);

        // Ottengo i riferimenti alle view
        vListaStudenti = findViewById(R.id.listaStudenti);

        // Ottengo un riferimento al datasource
        dataSource = DataSource.getInstance();

        // Creo l'adapter
        adapter = new StudentiAdapter(this, dataSource.getElencoStudenti("A13"));

        // Associo l'adapter alla listview
        vListaStudenti.setAdapter(adapter);

        // Imposto il listner per il click sulla listview
        vListaStudenti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // E' stato selezionato una riga della lista: devo visualizzare i dettagli
                // sulla nuova activity

                // Ottengo dall'adapter lo studente da visualizzare
                Studente studente = (Studente)adapter.getItem(i);

                // Creo l'Intent per passare alla activity con il dettaglio
                Intent intent = new Intent(view.getContext(), DettaglioStudenteActivity.class);

                intent.putExtra(EXTRA_STUDENTE, studente);
                startActivity(intent);
            }
        });
    }
}
