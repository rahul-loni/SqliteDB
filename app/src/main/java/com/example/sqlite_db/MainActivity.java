package com.example.sqlite_db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mdb;
    EditText editname,editlastname,text_marks,txt_id;
    Button btnInsert,view_Data,btnupdate;

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
        view_Data=(Button)findViewById(R.id.btn_view);
        txt_id=(EditText)findViewById(R.id.edit_id);
        btnupdate=(Button)findViewById(R.id.btn_update);


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

      view_Data.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Cursor cur = mdb.getAllData();
              if (cur.getCount() == 0) {
                  Toast.makeText(MainActivity.this, "Empty",
                          Toast.LENGTH_SHORT).show();
//                  showmassage("Error","No Data Found");
                  return;

              }
              StringBuffer buffer = new StringBuffer();
              while (cur.moveToNext()) {
                  buffer.append("ID" + cur.getString(0) + "\n");
                    buffer.append("name" + cur.getString(1) + "\n");
                  buffer.append("lastname" + cur.getString(2) + "\n");
                  buffer.append("marks" + cur.getString(3) + "\n");
              }
              show("Data",buffer.toString());
          }
      });
      btnupdate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              boolean isUpdate=mdb.updatedata(txt_id.getText().toString(),
                      editname.getText().toString(),editlastname.getText().toString(),
                      text_marks.getText().toString());
              if(isUpdate==true){
                  Toast.makeText(MainActivity.this, "Data Update",
                          Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(MainActivity.this, "Data Not Update",
                          Toast.LENGTH_SHORT).show();
              }
          }
      });
    }
    public void show(String title , String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}