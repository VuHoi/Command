package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import model.Command;
import vn.nks.sunny.phieucongtac.R;

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
        Command command=this.objects.get(position);
        txtID.setText(command.getSoPhieu());
        txtTench.setText(command.getChiHuyTrucTiep());
        txtTengs.setText(command.getGiamSatAnToan());
        txtTennd.setText(command.getNoiCongTac());
        txtdv.setText(command.getDonViYeuCau());
        return  row;
    }
}
