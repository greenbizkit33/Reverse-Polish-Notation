package com.nathanhaze.snapcalculator.ui.util;

import android.content.Context;
import android.content.SharedPreferences;

final public class UserPreference {

    private final String USER_SETTING = "user setting";
    private final String FIRST_TIME_USER = "first time user";

    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;
    private static UserPreference userPreference;

    private UserPreference(Context context) {
        sharedPref = context.getSharedPreferences(
                USER_SETTING, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public static UserPreference getInstance(Context context) {
        if (userPreference == null) {
            userPreference = new UserPreference(context);
        }
        return userPreference;
    }

    public boolean isFirstTimeUser() {
        if (sharedPref == null) {
            return true;
        }
        return sharedPref.getBoolean(FIRST_TIME_USER, true);
    }

    public void setFirstTimeUser(boolean isFirstTimeUser) {
        if (editor != null) {
            editor.putBoolean(FIRST_TIME_USER, isFirstTimeUser);
            editor.apply();
        }
    }

}
