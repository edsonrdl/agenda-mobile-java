package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    EditText et_name,et_phone;
    Button btn_record,btn_consult,btn_close;

    SQLiteDatabase db=null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=(EditText) findViewById(R.id.et_name);
        et_phone=(EditText) findViewById(R.id.et_phone);
        btn_record=(Button) findViewById(R.id.btn_record);
        btn_consult=(Button) findViewById(R.id.btn_consult);
        btn_close=(Button) findViewById(R.id.btn_close);
        openDb();
        closeDb();
        openOrCreateTable();

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
    public void searchData(){

    }
    public  void  openOrCreateTable(){
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos (id Integer PRIMARY KEY,nome TEXT,phone TEXT)");
        }catch (Exception ex){
            msg("Erro ao criar Tabela");
        }
    }


    public  void insertRecord(View v){
        String st_name,st_phone;
        st_name=et_name.getText().toString();
        st_phone=et_phone.getText().toString();
        if (st_name =="" || st_phone ==""){
            msg("Campos não podem estar vazios");
            return;
        }
        openDb();
     try {
         db.execSQL("INSERT INTO contatos (name,phone) VALUES ('"+st_name+","+st_phone+"')");
     }catch (Exception ex){
         msg("Erro ao inserir registro");
     }finally {
         msg("Registro inserido com sucesso");
     }
        closeDb();
     et_name.setText(null);
     et_phone.setText(null);
    }
    public void openDisplayConsult(View v){
        Intent it_display_consult=new Intent(this,DisplayConsult.class);
        startActivity(it_display_consult);
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