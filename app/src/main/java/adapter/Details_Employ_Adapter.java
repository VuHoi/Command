package adapter;

import android.app.Activity;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.Command_Detail_Employ;
import vn.nks.sunny.phieucongtac.LoginActivity;
import vn.nks.sunny.phieucongtac.R;

/**
 * Created by Billy on 10/16/2017.
 */

public class Details_Employ_Adapter   extends ArrayAdapter<Command_Detail_Employ> {
    Activity context;
    int resource;
    List<Command_Detail_Employ> objects;

    public Details_Employ_Adapter(Activity context, int resource, ArrayList<Command_Detail_Employ> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        final Command_Detail_Employ hoa = this.objects.get(position);

        Cursor cursor =LoginActivity.database.rawQuery("select username from user where id='"+hoa.getChuyenvien()+"'",null);
        cursor.moveToFirst();
        TextView textView=row.findViewById(R.id.textView3);
        textView.setText(cursor.getString(0));

        TextView textView1=row.findViewById(R.id.textView5);
        textView1.setText(hoa.getVaitro());

        ImageButton imageButton=row.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objects.remove(position);
                notifyDataSetChanged();
            }
        });
        return row;
    }
}
