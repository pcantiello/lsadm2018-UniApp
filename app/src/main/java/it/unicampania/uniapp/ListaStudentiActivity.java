package it.unicampania.uniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import it.unicampania.uniapp.datamodel.DataSource;
import it.unicampania.uniapp.datamodel.Studente;

// Activity con l'elenco degli studenti
public class ListaStudentiActivity extends AppCompatActivity {

    // Riferimenti alle view
    private ListView vListaStudenti;
    private TextView vFiltro;
    private Button vApplicaFiltro;

    // Adapter e data source
    private StudentiAdapter adapter;
    private DataSource dataSource;

    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_STUDENTE = "studente";
    private final String FILTRO_INIZIALE = "A13";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_studenti);

        // Ottengo i riferimenti alle view
        vListaStudenti = findViewById(R.id.listaStudenti);
        vFiltro = findViewById(R.id.editPrefisso);
        vApplicaFiltro = findViewById(R.id.btnFiltra);

        // Imposto la scritta del filtro iniziale
        vFiltro.setText(FILTRO_INIZIALE);

        // Disabilito la tastiera all'avvio dell'activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Ottengo un riferimento al datasource
        dataSource = DataSource.getInstance();

        // Creo l'adapter
        adapter = new StudentiAdapter(this, dataSource.getElencoStudenti(FILTRO_INIZIALE));

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

        // Imposto l'azione del pulsante relativo al filtro
        vApplicaFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filtro = vFiltro.getText().toString();
                if (filtro.length() < 3) {
                    Toast.makeText(getApplicationContext(), R.string.filtro_corto, Toast.LENGTH_SHORT).show();
                    // Riporto il focus sull'EditText
                    vFiltro.requestFocus();
                } else {

                    // Imposto il nuovo set di dati
                    adapter.setElencoStudenti(dataSource.getElencoStudenti(filtro));

                    // Nascondo la tastiera
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }
}
