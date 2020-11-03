package com.example.puzzlegame;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.ui.common.BaseActivity;

public class Help extends BaseActivity {
    WebView visitHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        visitHelp = (WebView) findViewById(R.id.visitHelp);
        final WebSettings visitHelpSettings = visitHelp.getSettings();
        visitHelpSettings.setJavaScriptEnabled(true);
        visitHelp.loadUrl("file:///android_asset/index.html");

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);
    }
}