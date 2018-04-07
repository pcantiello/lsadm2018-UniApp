package it.unicampania.uniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DettaglioStudenteActivity extends AppCompatActivity {

    // Riferimenti alle view
    TextView vMatricola;
    TextView vCognome;
    TextView vNome;
    TextView vCFU;
    TextView vMedia;

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
    }
}
