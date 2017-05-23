package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.AuthenticationServiceImpl;

public class WebClientActivity extends AppCompatActivity {

    @BindView(R.id.WebViewWebClientActivity) protected WebView webView;

    private String urlCompleta;
    private String urlSalida;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_client);

        Intent intent = getIntent();

        ButterKnife.bind(this);

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);

        urlCompleta = "https://www.themoviedb.org/authenticate/" + intent.getStringExtra("token");
        urlSalida = urlCompleta + "/allow";
        webView.loadUrl(urlCompleta);
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
                AuthenticationServiceImpl.getInstance().logIn(new TMDBClient.APICallback() {
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
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
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
