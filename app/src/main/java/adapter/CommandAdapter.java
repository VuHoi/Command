package adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import model.Command;
import vn.nks.sunny.phieucongtac.R;
import vn.nks.sunny.phieucongtac.WorkActivity;

/**
 * Created by Hoi on 10/16/2017.
 */

public class CommandAdapter extends ArrayAdapter<Command>
{
    Activity context;
    int resource;
    List<Command> objects;
CheckBox ckbdelete;
    public CommandAdapter(Activity context, int resource, List<Command> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtID=row.findViewById(R.id.txtID);
        TextView txtTench=row.findViewById(R.id.txtTench);
        TextView txtTengs=row.findViewById(R.id.txtTengs);
        TextView txtTennd=row.findViewById(R.id.txtTennd);
        TextView txtdv=row.findViewById(R.id.txtTendv);
        RelativeLayout rl=row.findViewById(R.id.rl);
        ckbdelete=row.findViewById(R.id.ckbdelete);
        final Command command=this.objects.get(position);
        if(command.getStatus().toString().equals("APPROVAL"))
        {
            rl.setBackgroundColor(Color.parseColor("#43A047"));
        }

            txtID.setText(command.getSoPhieu());
            txtTench.setText(command.getChiHuyTrucTiep());
            txtTengs.setText(command.getGiamSatAnToan());
            txtTennd.setText(command.getNoiCongTac());
            txtdv.setText(command.getDonViYeuCau());
        if(command.getCheck()==0)
        {
            ckbdelete.setVisibility(View.GONE);
        }
        else if(command.getCheck()!=0)
        {
            ckbdelete.setVisibility(View.VISIBLE);
        }
        ckbdelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                 objects.get(position).setCheck(2);
                else objects.get(position).setCheck(1);
//                int i=0;
//                for(Command command1:objects)
//                {
//
//                    if(command1.getCheck()==2)
//                    {
//                        i++;
//                        if(i==2)break;
//                    }
//
//                }
//                if(i==0){
//                    WorkActivity.fab1.setEnabled(false);
//                    WorkActivity.fab2.setEnabled(false);
//
//                }
//                else if(i==1)
//                {
//                    WorkActivity.fab1.setEnabled(true);
//                    WorkActivity.fab2.setEnabled(true);
//                }
//                else{
//                    WorkActivity.fab1.setEnabled(false);
//                    WorkActivity.fab2.setEnabled(true);
//                }

            }
        });
        return  row;
    }
}
