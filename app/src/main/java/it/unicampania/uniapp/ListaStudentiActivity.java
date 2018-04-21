package it.unicampania.uniapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private FloatingActionButton vAggiungi;

    // Adapter e data source
    private StudentiAdapter adapter;
    private DataSource dataSource;

    // Chiave per il passaggio parametri alle activity di dettaglio
    private final String EXTRA_STUDENTE = "studente";

    // Costanti con i result code
    private final int REQ_ADD_STUDENTE = 1;
    private final int REQ_EDIT_STUDENTE = 2;

    private final String TAG = "ListaStudenti";

    // Filtro attuale
    private String filtroCorrente = "A13";
    private String matricolaCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_studenti);

        // Ottengo i riferimenti alle view
        vListaStudenti = findViewById(R.id.listaStudenti);
        vFiltro = findViewById(R.id.editPrefisso);
        vApplicaFiltro = findViewById(R.id.btnFiltra);
        vAggiungi = findViewById(R.id.fabAggiungi);

        // Imposto la scritta del filtro iniziale
        vFiltro.setText(filtroCorrente);

        // Disabilito la tastiera all'avvio dell'activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Ottengo un riferimento al datasource
        dataSource = DataSource.getInstance();

        // Creo l'adapter
        adapter = new StudentiAdapter(this, dataSource.getElencoStudenti(filtroCorrente));

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
                filtroCorrente = vFiltro.getText().toString();
                if (filtroCorrente.length() < 3) {
                    Toast.makeText(getApplicationContext(), R.string.filtro_corto, Toast.LENGTH_SHORT).show();
                    // Riporto il focus sull'EditText
                    vFiltro.requestFocus();
                } else {

                    // Imposto il nuovo set di dati
                    adapter.setElencoStudenti(dataSource.getElencoStudenti(filtroCorrente));

                    // Nascondo la tastiera
                    InputMethodManager inputManager = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        // Imposto l'azione relativa al pulsante Aggiungi
        vAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditStudenteActivity.class);
                startActivityForResult(intent, REQ_ADD_STUDENTE);
            }
        });

        // Imposto il context menu sulle righe della listview
        registerForContextMenu(vListaStudenti);

    }

    // Processo dei valori di ritorno dalle altre activiy
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQ_ADD_STUDENTE :     // Activity di aggiunta
                if (resultCode == RESULT_OK) {   // Pulsante Ok premuto

                    // Estraggo le informazioni sullo studente da aggiungere
                    Studente studente = (Studente) data.getSerializableExtra(EXTRA_STUDENTE);

                    if (studente != null) {
                        // Aggiungo lo studente al datasource
                        dataSource.addStudente(studente);
                        // Imposto il nuovo set di dati
                        adapter.setElencoStudenti(dataSource.getElencoStudenti(filtroCorrente));
                    }
                }
                break;

            case REQ_EDIT_STUDENTE :    // Activity di modifica
                if (resultCode == RESULT_OK) {   // Pulsante Ok premuto

                    // Estraggo le informazioni sullo studente da modificare
                    Studente studente = (Studente) data.getSerializableExtra(EXTRA_STUDENTE);

                    if (studente != null) {
                        // Sostituisco lo studente nel datasource
                        dataSource.deleteStudente(matricolaCorrente);
                        dataSource.addStudente(studente);
                        // Imposto il nuovo set di dati
                        adapter.setElencoStudenti(dataSource.getElencoStudenti(filtroCorrente));
                    }
                }
                break;

            default:
                Log.v(TAG, "Result code non valido");
                break;
        }

    }

    // Creazione del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_studenti, menu);
    }

    // Selezione di un elemento nel context menu

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // All'interno di info.position posso leggere la posizione dell'elemento selezionato

        switch (item.getItemId()) {

            case R.id.itemDelete:
                // Eliminazione studente
                dataSource.deleteStudente(adapter.getItem(info.position).getMatricola());
                adapter.setElencoStudenti(dataSource.getElencoStudenti(filtroCorrente));
                return true;

            case R.id.itemEdit:
                // Modifica studente. Chiedo lo studente all'adapter e lo passo all'altra activiy
                Studente studente = adapter.getItem(info.position);
                matricolaCorrente = studente.getMatricola();    // Salvo la matricola per poterla eventualmente modificare
                Intent intent = new Intent(getApplicationContext(), EditStudenteActivity.class);
                intent.putExtra(EXTRA_STUDENTE, studente);
                // Faccio partire l'activiy in modalità edit
                startActivityForResult(intent, REQ_EDIT_STUDENTE);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
