package com.example.smartworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomBaseAdaptor extends BaseAdapter {


    Context context;
    String Exercise_Names[];
    int ax[];
    LayoutInflater inflater;

    public CustomBaseAdaptor (Context kir, String [] tamrin, int [] ax){
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


        convertView = inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView textView1 = convertView.findViewById(R.id.ExerNameTXT);
        ImageView imageView1 = convertView.findViewById(R.id.ExerImage);
        Button weightUpBTN = convertView.findViewById(R.id.UpBTNWeight);
        Button weightDownBTN = convertView.findViewById(R.id.DownBTNWeight);
        EditText editWeight = convertView.findViewById(R.id.editTextWeight);
        TextView setsXreps = convertView.findViewById(R.id.Num_Rep_TXT);

        //
            weightUpBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 editWeight.setText( String.valueOf (Float.parseFloat(editWeight.getText().toString()) + 0.5));
                }
            });

        weightDownBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float input_weight_ui = (float) (Float.parseFloat(editWeight.getText().toString()) - 0.5);
                if (input_weight_ui < 0) {
                    editWeight.setText("0.0");
                    Toast.makeText(context, "wrong input", Toast.LENGTH_SHORT).show();
                } else {
                editWeight.setText( String.valueOf (input_weight_ui));
            }
            }
        });
        //
        textView1.setText(Exercise_Names[position]);
        imageView1.setImageResource(ax[position]);
        return convertView;




    }


}
