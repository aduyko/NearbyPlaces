/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aduyko.nearby;
import java.io.*;
import java.net.*;
import java.util.Properties;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Andy
 */
public class GeoNamesHelper {
    private static GeoNamesHelper singleton = null;
    private String geoNamesUsername = null;
    // Limited to 2000 requests hourly, 30000 requests daily for a free user
    private static final String geoNamesBaseUrl = "http://api.geonames.org/findNearby?";

    // Populate user credentials for accessing the API using gitignored config file
    private GeoNamesHelper() {
        Properties geoNamesProperties = loadProperties();
        this.geoNamesUsername = geoNamesProperties.getProperty("geonames_username");
    }

    public static GeoNamesHelper getInstance() {
      if(singleton == null) {
         singleton = new GeoNamesHelper();
      }
      return singleton;
   }
    
    private static Properties loadProperties() {
        Properties geoNamesProperties = new Properties();
        try {
            InputStream input = new FileInputStream("config/config.properties");
            geoNamesProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoNamesProperties;
    }

    public Location findNearbyLocation(Location location) {
        String latitude = location.getLatitude();
        String longitude = location.getLongitude();
        Document xmlNearbyLocation = getNearbyLocationDoc(latitude,longitude);
        
        if (xmlNearbyLocation != null) {
            Element nearbyLatitude
                    = (Element) xmlNearbyLocation.getElementsByTagName("lat").item(0);
            Element nearbyLongitude
                    = (Element) xmlNearbyLocation.getElementsByTagName("lng").item(0);
            Element nearbyName
                    = (Element) xmlNearbyLocation.getElementsByTagName("asciiName").item(0);
            Element nearbyDistance
                    = (Element) xmlNearbyLocation.getElementsByTagName("distance").item(0);
            location.setLocationLatitude(nearbyLatitude.getTextContent());
            location.setLocationLongitude(nearbyLatitude.getTextContent());
            location.setLocationName(nearbyName.getTextContent());
            location.setLocationDistance(nearbyDistance.getTextContent());
        }
        return location;
    }
    
    private Document getNearbyLocationDoc(String latitude, String longitude) {
        String nearbyQuery = "style=full&lat=" + latitude + "&lng=" + longitude
                + "&username=" + geoNamesUsername;
        String nearbyUrl = geoNamesBaseUrl + nearbyQuery;
        Document xmlResponse = null;
        try {
            URL url = new URL(nearbyUrl);
            InputStream response = url.openStream();
            xmlResponse = parseGeoNamesResponse(response);
        } catch(Exception e) {
            System.out.println("There was an error communicating with the geonaming service");
        }
        return xmlResponse;
    }
    
    // Inputstream to xml parsing aided by:
    // http://lalajisureshika.blogspot.com/2010/11/convert-inputstream-to-xml.html
    private static Document parseGeoNamesResponse(InputStream input) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(input);
        } catch(ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Could not parse the response from the geonaming service");
        }
        return doc;
    }
}
