package uef.com.thigiuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public ListView lv;
    String[] layoutName;

    Button btnBack;

    static ArrayList<String> layoutNames = new ArrayList<>();
    static ArrayList<Integer> layoutIcons = new ArrayList<>();

    MainActivity2.myadapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        layoutName = getResources().getStringArray(R.array.layout_name_string);

        btnBack = findViewById(R.id.back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lv = (ListView) findViewById(R.id.listViewMain2);

        adapter = new myadapter(this, layoutNames, layoutIcons);
        lv.setAdapter(adapter);

        int dem = getIntent().getIntExtra("dem", 0);
        int vitri = getIntent().getIntExtra("ViTriChon", 0);

        int vitrithem = layoutNames.size() + dem;

        if (vitrithem < layoutNames.size())
        {
            layoutNames.add(vitrithem, MainActivity.layoutName[vitri]);
            layoutIcons.add(vitrithem, MainActivity.layoutIcon[vitri]);
        }
        else
        {
            layoutNames.add(MainActivity.layoutName[vitri]);
            layoutIcons.add(MainActivity.layoutIcon[vitri]);
        }

        adapter.notifyDataSetChanged();

    }

    public static class View_An_Item
    {
        public ImageView imageview;
        public TextView textview;
    }

    public class myadapter extends BaseAdapter
    {
        Context context;

        ArrayList<String> layoutNames;

        ArrayList<Integer> layoutIcons;

        public myadapter(Context context, ArrayList<String> layoutNames, ArrayList<Integer> layoutIcons) {
            this.context = context;
            this.layoutNames = layoutNames;
            this.layoutIcons = layoutIcons;
        }

        public myadapter(Context c)
        {
            context=c;
        }
        public int getCount() {
            // TODO Auto-generated method stub
            return layoutIcons.size();
        }
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return layoutIcons.get(position);
        }
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub
            View_An_Item an_item;
            LayoutInflater layoutinflater= ((Activity)context).getLayoutInflater();
            if(arg1==null)
            {
                an_item = new MainActivity2.View_An_Item();
                arg1 = layoutinflater.inflate(R.layout.list_item_icon, null);
                an_item.textview = (TextView) arg1.findViewById(R.id.textView1);
                an_item.imageview = (ImageView)arg1.findViewById(R.id.imageView1);
                arg1.setTag(an_item);
            }
            else
                an_item =(MainActivity2.View_An_Item)arg1.getTag();
            an_item.imageview.setImageResource(layoutIcons.get(arg0));
            an_item.textview.setText(layoutNames.get(arg0));
            return arg1;
        }
    }
}
