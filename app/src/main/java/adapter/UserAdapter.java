package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.User;
import vn.nks.sunny.phieucongtac.R;

/**
 * Created by Hoi on 10/16/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {

    Activity context;
    int resource;
    List<User> objects,tempCustomer,suggestions;
    public UserAdapter(Activity context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        tempCustomer=new ArrayList<User>(objects);
        suggestions=new ArrayList<User>(objects);;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView textView=row.findViewById(R.id.txta);
        TextView textView1=row.findViewById(R.id.txtb);
        TextView textView2=row.findViewById(R.id.txtc);



        final User nhanVien = this.objects.get(position);
        textView.setText( nhanVien.getUserName());
        textView1.setText( nhanVien.getLevel());
        textView2.setText( nhanVien.getUnit());

        return row;
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            User customer = (User) resultValue;
            return customer.getUserName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (User people : tempCustomer) {
                    if (people.getUserName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<User> c = (ArrayList<User>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (User cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };
}