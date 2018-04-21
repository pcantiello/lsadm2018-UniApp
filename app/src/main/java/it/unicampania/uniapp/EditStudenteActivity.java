package it.unicampania.uniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import it.unicampania.uniapp.datamodel.Studente;

// Activity per inserimento o modifica studente
public class EditStudenteActivity extends AppCompatActivity {

    // Chiave per il passaggio parametri alle activity di dettaglio
    private final String EXTRA_STUDENTE = "studente";

    // Riferimenti alle view
    EditText vMatricola;
    EditText vCognome;
    EditText vNome;
    EditText vCFU;
    EditText vMedia;
    Button vOk;
    Button vCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_studente);

        // Ottengo i riferimenti alle view
        vMatricola = findViewById(R.id.editMatricola);
        vCognome = findViewById(R.id.editCognome);
        vNome = findViewById(R.id.editNome);
        vCFU = findViewById(R.id.editCFU);
        vMedia = findViewById(R.id.editMedia);
        vOk = findViewById(R.id.btnOk);
        vCancel = findViewById(R.id.btnCancel);

        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        final Intent intent = getIntent();
        Studente studente = (Studente)intent.getSerializableExtra(EXTRA_STUDENTE);

        if (studente != null) {
            vMatricola.setText(studente.getMatricola());
            vCognome.setText(studente.getCognome());
            vNome.setText(studente.getNome());
            vCFU.setText(Integer.toString(studente.getCfu()));
            vMedia.setText(Double.toString(studente.getMedia()));
        }

        // Imposto l'azione del pulsanti ok
        vOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Studente studente = leggiDatiStudente();
                if (studente != null) { // Dati validi
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_STUDENTE, studente);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // Avviso che i dati sono obbligatori
                    Toast.makeText(getApplicationContext(), R.string.dati_obbligatori, Toast.LENGTH_LONG).show();
                }
            }
        });

        // Imposto l'azione del pulsante Cancel
        vCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }

    // Legge i dati dello studente dalla form e ne verifica la correttezza
    private Studente leggiDatiStudente() {

        // Leggo tutti i dati immessi
        Studente studente = new Studente();
        studente.setMatricola(vMatricola.getText().toString());
        studente.setNome(vNome.getText().toString());
        studente.setCognome(vCognome.getText().toString());
        try {
            studente.setCfu(Integer.parseInt(vCFU.getText().toString()));
        } catch (Exception e) {
            studente.setCfu(0);
        }
        try {
            studente.setMedia(Double.parseDouble(vMedia.getText().toString()));
        } catch (Exception e) {
            studente.setMedia(0.0);
        }

        if (studente.getMatricola().length() > 3
                && studente.getCognome().length() > 2
                && studente.getNome().length() > 2
                )
            return studente;
        else
            return null;
    }

}
