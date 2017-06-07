package com.nerdspoint.android.promoter.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.nerdspoint.android.promoter.R;

public class WeebView extends AppCompatActivity {

    WebView web;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_weeb_view);

       // web= (WebView) findViewById(R.id.webView);
        Intent i = getIntent();
       web= new WebView(this);
        web.setId(R.id.layout1);
        web.setScrollContainer(false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 50);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        web.setLayoutParams(params);
        // String url = i.getStringExtra("url");

        web.getSettings().setJavaScriptEnabled(true);

        //loads the WebView completely zoomed out
        web.getSettings().setLoadWithOverviewMode(true);

        //true makes the Webview have a normal viewport such as a normal desktop browser
        //when false the webview will have a viewport constrained to it's own dimensions
        web.getSettings().setUseWideViewPort(true);

        //override the web client to open all links in the same webview
        web.setWebViewClient(new MyWebViewClient());
        web.setWebChromeClient(new MyWebChromeClient());

        //Injects the supplied Java object into this WebView. The object is injected into the
        //JavaScript context of the main frame, using the supplied name. This allows the
        //Java object's public methods to be accessed from JavaScript.
        web.addJavascriptInterface(new JavaScriptInterface(this), "Android");

        //load the home page URL
        web.loadUrl("http://www.google.co.in");
    }

    //customize your web view client to open links from your own site in the
    //same web view otherwise just open the default browser activity with the URL
    private class MyWebViewClient extends WebViewClient {
        @Override
        @JavascriptInterface
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("demo.mysamplecode.com")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

    }

    private class MyWebChromeClient extends WebChromeClient {

        //display alert message in Web View
        @Override
        @JavascriptInterface
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("webView_activity", message);
            new AlertDialog.Builder(view.getContext())
                    .setMessage(message).setCancelable(true).show();
            result.confirm();
            return true;
        }

    }

    public class JavaScriptInterface {
        Context mContext;

        // Instantiate the interface and set the context

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        //using Javascript to call the finish activity
        @JavascriptInterface
        public void closeMyActivity() {
            finish();
        }

    }

    //Web view has record of all pages visited so you can go back and forth
    //just override the back button to go back in history if there is page
    //available for display
    @Override
    @JavascriptInterface
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
