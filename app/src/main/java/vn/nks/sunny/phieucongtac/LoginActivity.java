package vn.nks.sunny.phieucongtac;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.MyDatabaseAdapter;
import model.User;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText id,pass;
    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AddControl();
        AddEvent();
    }

    private void AddEvent() {
        final ArrayList<User>arrayList = new ArrayList<>();

        Cursor cursor=database.rawQuery("select * from user",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {

            arrayList.add(new User(cursor.getString(1),cursor.getString(2)));;
            cursor.moveToNext();
        }cursor.close();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idt=id.getText().toString();
                String passt=pass.getText().toString();
                for(User a:arrayList)
                {
                    if(idt.equals(a.getUserName()) && passt.equals(a.getPassword()))
                    {
                        Intent intent=new Intent(LoginActivity.this,WorkActivity.class);
                        if(idt.equals("admin") || idt.equals("user"))
                            intent.putExtra("permission",true); //Gửi permission
                        else   intent.putExtra("permission",false);  //Gửi permission
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(LoginActivity.this,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_LONG).show();
              //  Toast.makeText(LoginActivity.this,arrayList.get(1).values()+" "+arrayList.get(1),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void AddControl() {
        myDatabase = new MyDatabaseAdapter(this);
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        id=findViewById(R.id.edtuser);
        pass=findViewById(R.id.edtpass);
        btnLogin=findViewById(R.id.btndangnhap);

    }
}
