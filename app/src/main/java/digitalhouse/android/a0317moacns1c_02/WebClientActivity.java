package digitalhouse.android.a0317moacns1c_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.Authenticator;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;

public class WebClientActivity extends AppCompatActivity {

    /** Called when the activity is first created. */

    WebView web;
    String urlCompleta;
    String urlSalida;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_client);

        Intent intent = getIntent();

        web = (WebView) findViewById(R.id.WebViewWebClientActivity);
        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        urlCompleta = "https://www.themoviedb.org/authenticate/" + intent.getStringExtra("token");
        urlSalida = urlCompleta + "/allow";
        web.loadUrl(urlCompleta);
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub

            super.onPageStarted(view, url, favicon);

        }

        public void onPageFinished(WebView view, String url){
            super.onPageFinished(view, url);
            if(urlSalida.equals(url))
            {
                Authenticator.getInstance().getSession(new TMDBClient.APICallback() {
                    @Override
                    public void onSuccess(Object result) {
                        finish();
                    }
                });
            }
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

}
