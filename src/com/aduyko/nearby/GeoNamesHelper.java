/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aduyko.nearby;
import java.io.*;
import java.net.*;
import java.util.Properties;

/**
 *
 * @author Andy
 */
public class GeoNamesHelper {
    private static GeoNamesHelper singleton = null;
    private Properties properties = null;

    private GeoNamesHelper() {
        this.properties = getProperties();
    }

    public static GeoNamesHelper getInstance() {
      if(singleton == null) {
         singleton = new GeoNamesHelper();
      }
      return singleton;
   }
    
    private static Properties getProperties() {
        Properties geoNamesProperties = new Properties();
        try {
            InputStream input = new FileInputStream("config/config.properties");
            geoNamesProperties.load(input);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return geoNamesProperties;
    }

}
