package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Help extends AppCompatActivity {
    WebView visitHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        visitHelp = (WebView) findViewById(R.id.visitHelp);
        final WebSettings visitHelpSettings = visitHelp.getSettings();
        visitHelpSettings.setJavaScriptEnabled(true);
        visitHelp.loadUrl("file:///android_asset/myfile.html");
    }
}