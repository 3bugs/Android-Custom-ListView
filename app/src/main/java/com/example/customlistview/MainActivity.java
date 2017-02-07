package com.example.customlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton;

    private String[] mDataArray = {"a", "b", "c"};
    private boolean[] mStateArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStateArray = new boolean[mDataArray.length];
        for (boolean state : mStateArray) {
            state = false;
        }

        mListView = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(this, R.layout.list_item, mDataArray, mStateArray);
        mListView.setAdapter(adapter);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                for (int i = 0; i < mStateArray.length; i++) {
                    msg += i + ": " + mStateArray[i] + "\n";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    private static class MyAdapter extends ArrayAdapter<String> {

        private Context context;
        private int layoutResId;
        private String[] dataArray;
        private boolean[] stateArray;

        public MyAdapter(Context context, int resource, String[] objects, boolean[] stateArray) {
            super(context, resource, objects);

            this.context = context;
            this.layoutResId = resource;
            this.dataArray = objects;
            this.stateArray = stateArray;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemLayout = View.inflate(context, layoutResId, null);

            final TextView textView = (TextView) itemLayout.findViewById(R.id.text_view);
            textView.setText(dataArray[position]);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stateArray[position] = !stateArray[position];
                    if (stateArray[position]) {
                        textView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
                    } else {
                        textView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                    }
                }
            });

            return itemLayout;
        }

        @Override
        public int getCount() {
            return dataArray.length;
        }
    }
}
