package com.example.joo.codejam.managers;

import com.example.joo.codejam.models.UserAccessToken;

/**
 * Created by Joo on 2016-07-06.
 */
public interface NetworkManager {
    UserAccessToken getUserToken(String emailAddress, String password);
}
