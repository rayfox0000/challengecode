package com.example.hooch.medicsource;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

/**
 * Created by hooch on 20/3/2018.
 */

public class FeaturesAdapter extends BaseAdapter {
    String [] result;
    Context context;
    private static LayoutInflater inflater=null;

    public FeaturesAdapter(MenuActivity menuActivity, String[] functionList) {
// TODO Auto-generated constructor stub
        result=functionList;
        context=menuActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d("VALUE",result[0]);
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView function_tv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.features_list_layout, null);

        holder.function_tv=(TextView) view.findViewById(R.id.function_name);

        holder.function_tv.setText(result[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }



}
