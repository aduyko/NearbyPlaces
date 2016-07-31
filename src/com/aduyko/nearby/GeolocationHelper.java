/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aduyko.nearby;
import java.net.*;
// JSON Parsing
import com.google.gson.*;

/**
 *
 * @author Andy
 */
public class GeolocationHelper {

    private static GeolocationHelper singleton = null;

    private GeolocationHelper() {
    }

    public static GeolocationHelper getInstance() {
        if (singleton == null) {
            singleton = new GeolocationHelper();
        }
        return singleton;
    }
    
    public static Location findLocation(String inputIp) {
        Location location = new Location(inputIp);
        return location;
    }
    
    public static String getGeolocation(String inputIp) {
        return "";
    }
    
}
