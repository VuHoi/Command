package vn.nks.sunny.phieucongtac;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.Details_Employ_Adapter;
import adapter.MyDatabaseAdapter;

import model.Command;
import model.Command_Detail_Employ;
import model.User;


public class Add_Employ extends AppCompatActivity {
    Button btnadd;
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;

    ArrayList<Command_Detail_Employ> listEmploy;
    Details_Employ_Adapter arrayAdapter;
    ListView listView;

    String sophieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__employ);
        sophieu=getIntent().getStringExtra("sophieu");
        AddControl();
        AddEvent();

    }

    private void AddEvent() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Add_Employ.this);
                dialog.setContentView(R.layout.dialog_add_employ);

                final Spinner employ=dialog.findViewById(R.id.spn_em);
                final Spinner pos=dialog.findViewById(R.id.spn_pos);



 
                final ArrayList<User> listEm=new ArrayList<>();

                ArrayList<String> listPos=new ArrayList<>();

                Boolean flag1=true;
                for(Command_Detail_Employ command_detail_employ:listEmploy )
                {
                    if(command_detail_employ.getVaitro().toString().equals("Trưởng nhóm"));
                    {
                        flag1=false;
                        break;
                    }
                }
                listPos.add("Thành viên");
                //listPos.add("Vai trò");
                if(flag1) listPos.add("Trưởng nhóm");

                listEm.add(new User("Chọn nhân viên",null));


                Cursor cursor = database.rawQuery("SELECT * FROM user", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    User user=new User(cursor.getString(1),cursor.getInt(0));
                    Boolean flag=true;
                    for(Command_Detail_Employ command_detail_employ:listEmploy )
                    {
                        if(command_detail_employ.getChuyenvien()==user.getId())
                        {
                            flag=false;
                            break;
                        }
                    }
                    if(flag)listEm.add(user);
                    cursor.moveToNext();
                }


 
                ArrayAdapter<User> adapterEm=new ArrayAdapter<>(Add_Employ.this, android.R.layout.simple_spinner_item,listEm);
                adapterEm.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


                ArrayAdapter<String> adapterPos=new ArrayAdapter<String>(Add_Employ.this, android.R.layout.simple_spinner_item,listPos);
                adapterPos.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);



               employ.setAdapter(adapterEm);
               pos.setAdapter(adapterPos);


                Button btn_add_employ=dialog.findViewById(R.id.btn_add_employ);
                btn_add_employ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (employ.getSelectedItemPosition() == 0 )//|| pos.getSelectedItemPosition() == 0)
                            Toast.makeText(Add_Employ.this, "Chưa chọn nhân viên hoặc vai trò", Toast.LENGTH_LONG).show();
                        else
                        {

                            int posi=employ.getSelectedItemPosition();
                            listEmploy.add(new Command_Detail_Employ(null,listEm.get(posi).getId(),pos.getSelectedItem().toString(),0,null));
                            arrayAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();


            }
        });
    }

    private void AddControl() {
        myDatabase = new MyDatabaseAdapter(this);
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        btnadd=findViewById(R.id.btn_add);

        listEmploy=new ArrayList<>();
        arrayAdapter=new Details_Employ_Adapter(this,R.layout.item_command_employ,listEmploy);
        listView=findViewById(R.id.list_employ);
        listView.setAdapter(arrayAdapter);



        Cursor cursor=database.rawQuery("select * from phieuchitiet",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
           listEmploy.add(new Command_Detail_Employ(cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
           cursor.moveToNext();
        }cursor.close();

        database.delete("phieuchitiet","sophieu=?",new String[]{sophieu});

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Command_Detail_Employ command_detail_employ:listEmploy) {


                    ContentValues values = new ContentValues();
                    values.put("sophieu", sophieu);
                    values.put("chuyenvien", command_detail_employ.getChuyenvien());
                    values.put("vaitro", command_detail_employ.getVaitro());
                    database.insertWithOnConflict("phieuchitiet", null, values, SQLiteDatabase.CONFLICT_FAIL);
                }
                finish();
            }});
    }
}

