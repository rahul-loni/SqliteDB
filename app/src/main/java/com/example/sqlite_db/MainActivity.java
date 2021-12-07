package com.example.sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mdb;
    EditText editname,editlastname,text_marks;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdb=new DatabaseHelper(this);
//        databaseHelper.getWritableDatabase();

        editname=(EditText) findViewById(R.id.edit_name);
        editlastname=(EditText) findViewById(R.id.last_name);
        text_marks=(EditText) findViewById(R.id.marks);
        btnInsert=(Button) findViewById(R.id.btn_insert);

      btnInsert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
           boolean inserted= mdb.insertData(editname.getText().toString(),
                     editlastname.getText().toString(),
                     text_marks.getText().toString());
           if (inserted==true){
               Toast.makeText(MainActivity.this, "insert Data",
                       Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(MainActivity.this, "Data no insert",
                       Toast.LENGTH_SHORT).show();
           }
          }
      });
    }
}