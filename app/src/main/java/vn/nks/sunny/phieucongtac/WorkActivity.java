package vn.nks.sunny.phieucongtac;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.EOFException;
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
    Boolean permistion=false;

    @Override
    protected void onStart() {
        super.onStart();
        commands.clear();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM PHIEUCONGTAC ORDER BY POS AND STATUS='APPROVAL | PENDING'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Cursor cursor1 = database.rawQuery("select Username from User where id=? ", new String[]{cursor.getString(2)});
                cursor1.moveToFirst();

                Cursor   cursor2 = database.rawQuery("select Username from User where id=? ", new String[]{cursor.getString(3)});
                cursor2.moveToFirst();
                Cursor   cursor3 = database.rawQuery("select title from Donvi where id=? ", new String[]{cursor.getString(6)});
                cursor3.moveToFirst();
                Command command = new Command(cursor.getString(0), cursor1.getString(0), cursor2.getString(0), cursor.getString(4), cursor3.getString(0), cursor.getString(15),0);
                commands.add(command);
                cursor.moveToNext();
            }
        }catch (Exception e){}
        adapter.notifyDataSetChanged();

    }
    private Boolean isFabOpen = false;
    private FloatingActionButton fab1,fab2,fab3;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        lsvcongtac=findViewById(R.id.lsvcongtac);
        setTitle("Phiếu công tác");
        permistion=getIntent().getBooleanExtra("permission",true);

        myDatabase = new MyDatabaseAdapter(this);
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        fab=findViewById(R.id.fab);
        commands=new ArrayList<>();
        adapter=new CommandAdapter(this,R.layout.itemcommand,commands);
        lsvcongtac.setAdapter(adapter);
        fab =  findViewById(R.id.fab);
        fab1 =  findViewById(R.id.fab1);
        fab2 =  findViewById(R.id.fab2);
        fab3=findViewById(R.id.fab3);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        if(permistion)
        {
            fab.setVisibility(View.VISIBLE);
        }
        else { fab.setVisibility(View.GONE); }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFAB();
            }
        });

fab3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(WorkActivity.this,AddCommand.class);
        startActivity(intent);
    }
});

fab2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        for (int i=0;i<commands.size();i++ )
        {
            if(commands.get(i).getCheck()==2)
            {
                try {
                    database.delete("phieucongtac", "sophieu=?", new String[]{commands.get(i).getSoPhieu().toString()});
                    commands.remove(i);
                    adapter.notifyDataSetChanged();
                    i--;
                }catch (Exception e){}
            }
        }
        fabClose();
    }
});
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

for(Command command:commands)
{
    if(command.getCheck()==2)
    {
        Intent intent=new Intent(WorkActivity.this,AddCommand.class);
        intent.putExtra("command1",command);
        startActivity(intent);
        break;
    }
}




                fabClose();
            }
        });



        lsvcongtac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
Intent intent=new Intent(WorkActivity.this,ViewActivity.class);
intent.putExtra("command",   commands.get(i));
startActivity(intent);
            }
        });



        lsvcongtac.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                for (Command command : commands )
                {
command.setCheck(1);
adapter.notifyDataSetChanged();
                }
                fabOpen();
                return false;
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
    public void animateFAB(){

        if(isFabOpen){

            fabClose();

        } else {

          fabOpen();

        }
    }


    void fabOpen()
    {
        fab.startAnimation(rotate_forward);
        fab1.startAnimation(fab_open);
        fab2.startAnimation(fab_open);
        fab3.startAnimation(fab_open);
        fab1.setClickable(true);
        fab2.setClickable(true);
        fab3.setClickable(true);
        isFabOpen = true;
        for(Command command:commands)
        {
            command.setCheck(1);
            adapter.notifyDataSetChanged();
        }
        Log.d("Raj","open");
    }

    void fabClose()
    {
        fab.startAnimation(rotate_backward);
        fab1.startAnimation(fab_close);
        fab2.startAnimation(fab_close);
        fab3.startAnimation(fab_close);
        fab1.setClickable(false);
        fab2.setClickable(false);
        fab3.setClickable(false);
        isFabOpen = false;
        for(Command command:commands)
        {
            command.setCheck(0);
            adapter.notifyDataSetChanged();
        }
        Log.d("Raj", "close");
    }
}
