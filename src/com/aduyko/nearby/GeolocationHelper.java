/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aduyko.nearby;
import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Andy
 */
public class GeolocationHelper {
    private static GeolocationHelper singleton = null;
    // Limited to 10000 calls per hour
    private static final String geolocationBaseUrl = "http://freegeoip.net/xml/";

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
        Document xmlGeolocation = getGeolocationDoc(inputIp);
        if(xmlGeolocation != null) {
            Element latitude =
                (Element) xmlGeolocation.getElementsByTagName("Latitude").item(0);
            Element longitude =
                (Element) xmlGeolocation.getElementsByTagName("Longitude").item(0);
            location.setLatitude(latitude.getTextContent());
            location.setLongitude(longitude.getTextContent());
        }
        return location;
    }
    
    public static Document getGeolocationDoc(String inputIp) {
        String geolocationUrl = geolocationBaseUrl + inputIp;
        Document xmlResponse = null;
        try {
            URL url = new URL(geolocationUrl);
            InputStream response = url.openStream();
            xmlResponse = parseGeolocationResponse(response);
        } catch(Exception e) {
            System.out.println("There was an error communicating with the geolocation service");
        }
        return xmlResponse;
    }
    
    // Inputstream to xml parsing aided by:
    // http://lalajisureshika.blogspot.com/2010/11/convert-inputstream-to-xml.html
    public static Document parseGeolocationResponse(InputStream input) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(input);
        } catch(ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Could not parse the response from the geolocation service");
        }
        return doc;
    }
}
