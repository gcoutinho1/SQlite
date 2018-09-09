package sqlite.coutinhocompany.com.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {


        //criação do banco de dados
        SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

        //criação da tabela
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( nome VARCHAR, idade INT(3) ) ");

        //Inserir dados na tabela
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Marcos', 30) ");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Ana', 20) ");

        //recuperar dados na tabela
        Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);

        //recuperar indices das colunas
        int indiceColunaNome = cursor.getColumnIndex("nome");
        int indiceColunaIdade = cursor.getColumnIndex("idade");

        cursor.moveToFirst();

        while (cursor != null) {

            Log.i("RESULTADO = nome: ", cursor.getString(indiceColunaNome));
            Log.i("RESULTADO = idade: ", cursor.getString(indiceColunaIdade));
            cursor.moveToNext();
        }



        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
