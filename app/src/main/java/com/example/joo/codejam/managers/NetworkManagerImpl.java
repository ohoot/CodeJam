package com.example.joo.codejam.managers;

import com.example.joo.codejam.models.UserAccessToken;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkManagerImpl implements NetworkManager {

    private static final String USER_TOKEN_URL_FORMAT = "https://api-stg.getaim.co/v1/auth";
    private static final String REQUEST_HEADER_NAME_USER_TOKEN = "Content-Type";
    private static final String REQUEST_HEADER_VALUE_USER_TOKEN = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE = "password";
    private static final String CLIENT_ID = "MjlkZjFlZDItMjM3MS00MmE3LWJiZjEtMjA1YTAzMDVmMWJk";
    private static final String CLIENT_SECRET = "ODc1MzVkMjMtYTg3YS00MDE4LTg3ZTgtYWE2MjRkY2I0NjE5";

    final OkHttpClient client = new OkHttpClient();
    UserAccessToken userAccessToken;

    @Override
    public UserAccessToken getUserToken(String emailAddress, String password) {
        // {"grant_type"}: "password"
        // {"username"} : "ohootkk@gmail.com"
        // {"password"} : "Qwas1234"
        // {"client_id"} : "MjlkZjFlZDItMjM3MS00MmE3LWJiZjEtMjA1YTAzMDVmMWJk"
        // {"client_secret"} : "ODc1MzVkMjMtYTg3YS00MDE4LTg3ZTgtYWE2MjRkY2I0NjE5"

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", GRANT_TYPE)
                .add("username", emailAddress)
                .add("password", password)
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                .build();
//
        Request request = new Request.Builder()
                .url(USER_TOKEN_URL_FORMAT)
                .post(requestBody)
                .header(REQUEST_HEADER_NAME_USER_TOKEN, REQUEST_HEADER_VALUE_USER_TOKEN)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Gson gson = new Gson();
                userAccessToken = gson.fromJson(response.body().string(), UserAccessToken.class);
            }
        });
        return userAccessToken;
    }
}
