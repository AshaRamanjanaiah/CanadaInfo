package com.asha.canadainfoapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asha.canadainfoapp.data.TitleData;
import com.asha.canadainfoapp.service.ServiceAPI;
import com.asha.canadainfoapp.service.ServiceAPICallback;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements ServiceAPICallback {

    private ServiceAPI serviceAPI;
    private ProgressDialog dialog;

    private TextView textView;
    private int arraylen;
    private String[] subTitle;
    private String[] description;
    private String[] imageHref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceAPI = new ServiceAPI(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        serviceAPI.refreshAPI();

    }

    @Override
    public void serviceSuccess(TitleData titleData) {
        dialog.hide();

        String title = titleData.getTitle();

       subTitle = titleData.getRowsData().getSubtitle();
       description = titleData.getRowsData().getDescription();
       imageHref = titleData.getRowsData().getImageHref();

       arraylen = titleData.getRowsData().getArrayLen();

        ListView listView = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        // Find the toolbar view inside the activity layout
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(title);

    }

    @Override
    public void serviceFailure(Exception error) {
        dialog.hide();
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arraylen;
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

            convertView = getLayoutInflater().inflate(R.layout.custom_layout, null);

            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
            TextView textView_title = (TextView)convertView.findViewById(R.id.textView_title);
            TextView textView_description = (TextView)convertView.findViewById(R.id.textView_description);

            textView_title.setText(subTitle[position]);
            textView_description.setText(description[position]);

            // using glide image caching library to load images
            Glide.with(MainActivity.this).load(imageHref[position]).into(imageView);

            return convertView;
        }
    }

}

