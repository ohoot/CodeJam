package com.example.joo.codejam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.joo.codejam.models.UserAccessToken;
import com.example.joo.codejam.managers.NetworkManager;
import com.example.joo.codejam.managers.NetworkManagerImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_email) EditText editEmail;
    @BindView(R.id.edit_password) EditText editPassword;

    NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        networkManager = new NetworkManagerImpl();
    }

    @OnClick(R.id.btn_login)
    public void btnLoginClick() {
        final String emailAddress = editEmail.getText().toString();
        final String password = editPassword.getText().toString();

        Observable.just(networkManager.getUserToken(emailAddress, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserAccessToken>() {
                    @Override
                    public void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserAccessToken userAccessToken) {
                        if (userAccessToken != null) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.ACCESS_TOKEN_KEY, userAccessToken);
                            startActivity(intent);
                        }
                    }
                });
    }
}
