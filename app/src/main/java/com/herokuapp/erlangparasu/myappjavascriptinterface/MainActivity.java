package com.herokuapp.erlangparasu.myappjavascriptinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Tag." + MainActivity.class.getSimpleName();

    private WebView mWebView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupViews();
        reloadWebView();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");

        super.onResume();

        reloadWebView();
    }

    private void initViews() {
        Log.d(TAG, "initViews: ");

        mEditText = (EditText) findViewById(R.id.edit_text_1);
        mWebView = (WebView) findViewById(R.id.webview_1);
    }

    private void setupViews() {
        Log.d(TAG, "setupViews: ");

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(
                new WebAppInterface(this, mEditText),
                "MyJsInterface"
        );

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do nothing.
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: ");

                reloadWebView();
            }
        });
    }

    private void reloadWebView() {
        Log.d(TAG, "reloadWebView: ");

        mWebView.loadData("" +
                        "<html>" +
                        "<head>" +
                        "</head>" +
                        "<body>" +
                        "\n" +
                        "    <script language=\"javascript\" " +
                        "            type=\"text/javascript\">" +
                        "        var sampleText = MyJsInterface.getInputText();" +
                        "        document.write(sampleText);" +
                        "    </script>" +
                        "\n" +
                        "</body>" +
                        "</html>",

                "text/html",
                "UTF-8");
    }
}
