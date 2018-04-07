package it.unicampania.uniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import it.unicampania.uniapp.datamodel.Studente;

public class DettaglioStudenteActivity extends AppCompatActivity {

    // Riferimenti alle view
    TextView vMatricola;
    TextView vCognome;
    TextView vNome;
    TextView vCFU;
    TextView vMedia;

    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_STUDENTE = "studente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_studente);

        // Imposto i riferimenti alle view
        vMatricola = findViewById(R.id.textMatricola);
        vCognome = findViewById(R.id.textCognome);
        vNome = findViewById(R.id.textNome);
        vCFU = findViewById(R.id.textCFU);
        vMedia = findViewById(R.id.textMedia);

        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        Intent intent = getIntent();
        Studente studente = (Studente)intent.getSerializableExtra(EXTRA_STUDENTE);

        if (studente != null) {
            vMatricola.setText(studente.getMatricola());
            vCognome.setText(studente.getCognome());
            vNome.setText(studente.getNome());
            vCFU.setText(Integer.toString(studente.getCfu()));
            vMedia.setText(Double.toString(studente.getMedia()));
        }
    }
}
