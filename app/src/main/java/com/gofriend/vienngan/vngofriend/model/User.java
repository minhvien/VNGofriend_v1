package com.gofriend.vienngan.vngofriend.model;

import com.parse.ParseUser;

/**
 * Created by tmvien on 12/9/15.
 */
public class User extends ParseUser {
    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }
}
