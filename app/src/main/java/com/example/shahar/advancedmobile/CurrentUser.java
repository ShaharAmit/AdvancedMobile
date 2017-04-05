package com.example.shahar.advancedmobile;

import android.support.annotation.Keep;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Exclude;
import com.google.firebase.iid.FirebaseInstanceId;

class CurrentUser {

    static final String DB_REF = "users";

    private FirebaseUser userData = null;

    CurrentUser() {
    }

    public String getToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    boolean isAnonymous() {
        return userData == null || userData.isAnonymous();
    }


    @Keep // the "Keep" annotation is useless without corresponding ProGuard rule to keep it. (todo)
    public String getUserName() {
        if (userData != null) {
            return userData.getDisplayName();
        }
        return "-";
    }

    @Keep
    public String getEmail() {
        if (userData != null) {
            return userData.getEmail();
        }
        return "-";
    }

    @Exclude
    FirebaseUser getUserData() {
        return userData;
    }

    void setUserData(FirebaseUser userData) {
        this.userData = userData;
    }


}