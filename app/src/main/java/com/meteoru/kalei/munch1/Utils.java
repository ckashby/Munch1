package com.meteoru.kalei.munch1;

/**
 * Created by kalei on 2/14/2016.
 */
public class Utils {

    public static String formatBody(String input) {
        if (input.length() < 140) {
                return input;
            } else {
            return input.substring(0, 139);
        }
    }
}
