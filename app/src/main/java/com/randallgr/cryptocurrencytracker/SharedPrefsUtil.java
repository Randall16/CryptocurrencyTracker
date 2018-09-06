/**
 *  This class is solely for storing/managing the various Strings I used while working with
 *  Android's SharedPreferences API. This class should NEVER be instantiated.
 */

package com.randallgr.cryptocurrencytracker;

public final class SharedPrefsUtil {

    public static final String USER_PREFERENCES = "userPrefs";
    public static final String PRELOAD = "preload";
    public static final String DOMESTIC_TICKER = "domestic ticker";

    private SharedPrefsUtil() {
        // empty private constructor to prevent it from being instantiated
    }
}
