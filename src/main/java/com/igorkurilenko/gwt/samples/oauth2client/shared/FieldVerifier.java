package com.igorkurilenko.gwt.samples.oauth2client.shared;


public class FieldVerifier {
    public static boolean isValidName(String name) {
        return name != null && name.length() > 3;
    }
}
