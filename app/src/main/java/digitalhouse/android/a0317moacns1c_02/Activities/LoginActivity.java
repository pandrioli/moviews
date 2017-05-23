package digitalhouse.android.a0317moacns1c_02.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.Authentication;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.Authentication.RequestToken;
import digitalhouse.android.a0317moacns1c_02.R;
import digitalhouse.android.a0317moacns1c_02.Services.AuthenticationServiceImpl;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.textViewResultLogIn) protected TextView textViewResultLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        testSetTextViewResultLogIn();
    }

    private void testSetTextViewResultLogIn(){
        if(AuthenticationServiceImpl.getInstance().userIsLogged()){
            textViewResultLogIn.setTextColor(Color.GREEN);
            textViewResultLogIn.setText("LOGEADO! :)");
        }
        else{
            textViewResultLogIn.setTextColor(Color.RED);
            textViewResultLogIn.setText("NO LOGEADO");
        }
    }

    @OnClick(R.id.buttonToWebClientActivity)
    public void onClick(View v) {
        final RequestToken[] requestToken = new RequestToken[1];

        AuthenticationServiceImpl.getInstance().obtainRequestToken(new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                startWebClientAcivity((String)result);
            }
        });
    }

    public void startWebClientAcivity(String token) {
        Intent unIntent = new Intent(this, WebClientActivity.class);
        unIntent.putExtra("token", token);
        startActivity(unIntent);
    }
}
