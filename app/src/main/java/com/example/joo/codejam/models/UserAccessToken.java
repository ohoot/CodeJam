package com.example.joo.codejam.models;

import java.io.Serializable;

/**
 * Created by Joo on 2016-07-06.
 */
public class UserAccessToken implements Serializable{
    // "access_token": "xxxxxxoooooooooooooooooo",
    // "token_type": ""Bearer"",
    // "expires_in": 3600,
    // "scope": "sign_up_complete"

    public String access_token;
    public String token_type;
    public int expires_in;
    public String scope;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("access_token : " + access_token)
                .append(", token_type : " + token_type)
                .append(", expires_in : " + expires_in)
                .append(", scope : " + scope);
        return stringBuilder.toString();
    }
}
