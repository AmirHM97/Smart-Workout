package com.example.smartworkout;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomBaseAdaptor_2 extends BaseAdapter {


    Context context;
    String Exercise_Names[];
    int ax[];
    LayoutInflater inflater;


    public CustomBaseAdaptor_2 (Context kir, String [] tamrin, int [] ax){
        this.context = kir;
        this.Exercise_Names=tamrin;
        this.ax=ax;
        inflater=LayoutInflater.from(kir);
    }


    @Override
    public int getCount() {
        return Exercise_Names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        convertView = inflater.inflate(R.layout.activity_custom_list_view_2,null);
        TextView textView1 = convertView.findViewById(R.id.ExerNameTXT);
        ImageView imageView1 = convertView.findViewById(R.id.ExerImage);


        textView1.setText(Exercise_Names[position]);
        imageView1.setImageResource(ax[position]);


        return convertView;


    }


}
