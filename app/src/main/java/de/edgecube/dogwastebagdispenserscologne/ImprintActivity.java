package de.edgecube.dogwastebagdispenserscologne;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ImprintActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprint);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btn_data).setOnClickListener(this);
        findViewById(R.id.btn_license).setOnClickListener(this);
        findViewById(R.id.btn_github).setOnClickListener(this);
        findViewById(R.id.btn_linkedin).setOnClickListener(this);
        findViewById(R.id.btn_twitter).setOnClickListener(this);
        findViewById(R.id.btn_website).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        String url;
        switch (id) {
            case R.id.btn_data:
                url = getResources().getString(R.string.data_url);
                break;
            case R.id.btn_license:
                url = getResources().getString(R.string.license_url);
                break;
            case R.id.btn_github:
                url = getResources().getString(R.string.source_code_url);
                break;
            case R.id.btn_linkedin:
                url = getResources().getString(R.string.linkedin_url);
                break;
            case R.id.btn_twitter:
                url = getResources().getString(R.string.twitter_url);
                break;
            case R.id.btn_website:
                url = getResources().getString(R.string.website_url);
                break;
            default:
                return;
        }

        openUrl(url);
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
