package vn.nks.sunny.phieucongtac;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.CommandAdapter;
import adapter.MyDatabaseAdapter;
import model.Command;

public class WorkActivity extends AppCompatActivity {
  ListView lsvcongtac;
    FloatingActionButton fab;
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;
    List<Command> commands;
    CommandAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        lsvcongtac=findViewById(R.id.lsvcongtac);
        setTitle("Phiếu công tác");

        Toast.makeText(this, getIntent().getBooleanExtra("permission",false)+" ", Toast.LENGTH_SHORT).show();
        myDatabase = new MyDatabaseAdapter(this);
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        fab=findViewById(R.id.fab);
        commands=new ArrayList<>();
        adapter=new CommandAdapter(this,R.layout.itemcommand,commands);
        lsvcongtac.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkActivity.this,AddCommand.class);
                startActivity(intent);
            }
        });
        Cursor cursor = database.rawQuery("SELECT * FROM PHIEUCONGTAC ORDER BY POS AND STATUS='APPROVAL | PENDING'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
         Command  command=new Command(cursor.getString(0),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(6));
         commands.add(command);
            cursor.moveToNext();
        }
adapter.notifyDataSetChanged();


        lsvcongtac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.itacount)
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
