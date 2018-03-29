package com.herokuapp.erlangparasu.myappjavascriptinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Ref: https://developer.android.com/guide/webapps/webview.html
 */
public class WebAppInterface {

    Context mContext;
    EditText mEditText;

    /**
     * Instantiate the interface and set the context
     */
    WebAppInterface(Context context, EditText editText) {
        mContext = context;
        mEditText = editText;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String getInputText() {
        return mEditText.getText().toString();
    }
}
