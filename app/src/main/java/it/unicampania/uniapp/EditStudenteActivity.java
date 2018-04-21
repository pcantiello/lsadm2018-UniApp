package it.unicampania.uniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

// Activity per inserimento o modifica studente
public class EditStudenteActivity extends AppCompatActivity {

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
    }
}
