package digitalhouse.android.a0317moacns1c_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.AuthenticationEntities.RequestToken;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication.Authenticator;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.botonLoco);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickEsto();
            }
        });
    }

    public void OnClickEsto() {
        final RequestToken[] requestToken = new RequestToken[1];

       Authenticator.getInstance().getRequestToken(new TMDBClient.APICallback() {
           @Override
           public void onSuccess(Object result) {
               requestToken[0] = (RequestToken) result;
               startWebClientAcivity(requestToken[0].getRequest_token());
           }
       });
    }

    public void startWebClientAcivity(String token) {
        Intent unIntent = new Intent(this, WebClientActivity.class);
        unIntent.putExtra("token", token);
        startActivity(unIntent);
    }
}
