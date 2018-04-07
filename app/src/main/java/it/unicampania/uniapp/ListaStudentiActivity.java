package it.unicampania.uniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import it.unicampania.uniapp.datamodel.DataSource;

// Activity con l'elenco degli studenti
public class ListaStudentiActivity extends AppCompatActivity {

    // Riferimenti alle view
    private ListView vListaStudenti;

    // Adapter e data source
    private StudentiAdapter adapter;
    private DataSource dataSource;

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
    }
}
