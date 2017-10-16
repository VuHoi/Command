package vn.nks.sunny.phieucongtac;

import android.app.Dialog;
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

import adapter.MyDatabaseAdapter;
import model.Command;

public class Add_Employ extends AppCompatActivity {
    Button btnadd;
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;

    ArrayList<String> listEmploy;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__employ);
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



                final ArrayList<String> listEm=new ArrayList<>();
                ArrayList<String> listPos=new ArrayList<>();

                listPos.add("Vai trò");              listPos.add("Trưởng nhóm");         listPos.add("Thành viên");
                listEm.add("Chọn nhân viên");

                Cursor cursor = database.rawQuery("SELECT * FROM user", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    listEm.add(cursor.getString(1));
                    cursor.moveToNext();
                }


                ArrayAdapter<String> adapterEm=new ArrayAdapter<String>(Add_Employ.this, android.R.layout.simple_spinner_item,listEm);
                adapterEm.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


                ArrayAdapter<String> adapterPos=new ArrayAdapter<String>(Add_Employ.this, android.R.layout.simple_spinner_item,listPos);
                adapterPos.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);



               employ.setAdapter(adapterEm);
               pos.setAdapter(adapterPos);


                Button btn_add_employ=dialog.findViewById(R.id.btn_add_employ);
                btn_add_employ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (employ.getSelectedItemPosition() == 0 || pos.getSelectedItemPosition() == 0)
                            Toast.makeText(Add_Employ.this, "Chưa chọn nhân viên hoặc vai trò", Toast.LENGTH_LONG).show();
                        else
                        {
                            listEmploy.add(employ.getSelectedItem()+" - "+pos.getSelectedItem());
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
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listEmploy);
        listView=findViewById(R.id.list_employ);
        listView.setAdapter(arrayAdapter);

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
