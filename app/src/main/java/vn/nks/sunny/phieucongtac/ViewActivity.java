package vn.nks.sunny.phieucongtac;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import adapter.MyDatabaseAdapter;
import model.Command;

public class ViewActivity extends AppCompatActivity {

    MyDatabaseAdapter myDatabase;
    SQLiteDatabase database;
    Command commandRequest;
    TextView txtndct, txtsp,txtnlp,txtchtt,txtgsat,txtnct,txtdvyc,txtdk,txtnbd,txtnkt,txtdc,txtpt,txtrc,txtvt,txtgc,txttt;
   LinearLayout lncontainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Addcontrol();
        AddEvent();
    }

    private void AddEvent() {

        try {
            Cursor cursor = database.rawQuery("select  * from PhieuCongTac where sophieu=? ", new String[]{commandRequest.getSoPhieu()});
            cursor.moveToFirst();
            txtsp.setText(cursor.getString(0));
            txtnlp.setText(cursor.getString(1));
            Cursor cursor1 = database.rawQuery("select Username from User where id=? ", new String[]{cursor.getString(2)});
            cursor1.moveToFirst();
            txtchtt.setText(cursor1.getString(0));
            cursor1 = database.rawQuery("select Username from User where id=? ", new String[]{cursor.getString(3)});
            cursor1.moveToFirst();
            txtgsat.setText(cursor1.getString(0));
            txtnct.setText(cursor.getString(4));
            txtndct.setText(cursor.getString(5));
         cursor1 = database.rawQuery("select title from Donvi where id=? ", new String[]{cursor.getString(6)});
            cursor1.moveToFirst();
            txtdvyc.setText(cursor1.getString(0));
            txtdk.setText(cursor.getString(7));
            txtnbd.setText(cursor.getString(8));
            txtnkt.setText(cursor.getString(9));
            txtdc.setText(cursor.getString(10));
            cursor1 = database.rawQuery("select  title,soxe  from phuongtien where id=? ", new String[]{cursor.getString(11)});
            cursor1.moveToFirst();
            txtpt.setText(cursor1.getString(0) + " " + cursor1.getString(1));
            txtrc.setText(cursor.getString(12));
            txtvt.setText(cursor.getString(13));
            txtgc.setText(cursor.getString(14));
            txttt.setText(cursor.getString(15));
        }catch (Exception e){}
        try {
            Cursor cursor = database.rawQuery("select  * from PHIEUCHITIET where sophieu=? ", new String[]{commandRequest.getSoPhieu()});
            cursor.moveToFirst();
            Toast.makeText(this, cursor.getString(0) + "", Toast.LENGTH_SHORT).show();
            while (!cursor.isAfterLast()) {

                Cursor cursor1 = database.rawQuery("select  * from User where id=? ", new String[]{cursor.getString(2).toString()});
                cursor1.moveToFirst();
                final View item = LayoutInflater.from(ViewActivity.this).inflate(R.layout.itemmember, null);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                item.setLayoutParams(param);
                TextView txtname = item.findViewById(R.id.txtname);
                TextView txtlevel = item.findViewById(R.id.txtlevel);
                TextView txtUnit = item.findViewById(R.id.txtUnit);
                txtname.setText(cursor1.getString(1));
                txtlevel.setText(cursor1.getString(3));

                Cursor cursor2 = database.rawQuery("select  title from Donvi where id=? ", new String[]{cursor1.getString(4).toString()});
                cursor2.moveToFirst();
                txtUnit.setText(cursor2.getString(0));

                lncontainer.addView(item);
                cursor.moveToNext();
            }
        }catch (Exception e){}

    }

    private void Addcontrol() {
        txtsp=findViewById(R.id.txtsp);
        txtnlp=findViewById(R.id.txtnlp);
        txtchtt=findViewById(R.id.txtchtt);
        txtgsat=findViewById(R.id.txtgsat);
        txtnct=findViewById(R.id.txtnct);
        txtndct=findViewById(R.id.txtndct);
        txtdvyc=findViewById(R.id.txtdvyc);
        txtdk=findViewById(R.id.txtdk);
        txtnbd=findViewById(R.id.txtnbd);
        txtnkt=findViewById(R.id.txtnkt);
        txtdc=findViewById(R.id.txtdc);
        txtpt=findViewById(R.id.txtpt);
        txtrc=findViewById(R.id.txtrc);
        txtgc=findViewById(R.id.txtgc);
        txtvt=findViewById(R.id.txtvt);
lncontainer=findViewById(R.id.lncontainer);
        txttt=findViewById(R.id.txttt);
        myDatabase = new MyDatabaseAdapter(this);
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        commandRequest= (Command) getIntent().getSerializableExtra("command");


    }
}
