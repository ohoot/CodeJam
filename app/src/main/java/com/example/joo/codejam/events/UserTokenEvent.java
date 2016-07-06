package com.example.joo.codejam.events;

import com.example.joo.codejam.models.UserAccessToken;

/**
 * Created by Joo on 2016-07-06.
 */
public class UserTokenEvent {
    public UserAccessToken userAccessToken;

    public UserTokenEvent(UserAccessToken userAccessToken) {
        this.userAccessToken = userAccessToken;
    }
}
