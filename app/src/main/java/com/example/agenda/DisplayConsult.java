package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DisplayConsult extends AppCompatActivity {
    EditText et_name,et_phone;
    Button btn_previous,btn_next,btn_return;
    SQLiteDatabase db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_consult);

        et_name=(EditText) findViewById(R.id.et_name_consult);
        et_phone=(EditText) findViewById(R.id.et_phone_consult);
        btn_previous=(Button) findViewById(R.id.btn_previous_consult);
        btn_next=(Button) findViewById(R.id.btn_next_consult);
        btn_return=(Button) findViewById(R.id.btn_return_consult);
    }
    public  void  openDb(){
        try {

            db=openOrCreateDatabase("bancoAgenda",MODE_PRIVATE,null);

        }catch (Exception ex){
            msg("Erro ao abrir ou criar banco de dados");
        }
    }
    public  void closeDb(){
        db.close();
    }
    public void closeDisplayConsult(View v){
       this.finish();
    }
    public  void  msg(String txt){
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("Ok",null);
        adb.show();
    }
}