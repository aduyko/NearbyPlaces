/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aduyko.nearby;
import java.util.Scanner;
// Importing validator to check for ipv6 address validity
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 *
 * @author Andy
 */
public class NearbyPlaces {

    /**
     * @param args the command line arguments
     * args[0] can optionally be the IP you want to find a place near
     * If it isn't provided, you will be prompted to enter an IP address
     */
    public static void main(String[] args) {
        String inputIp;
        if(args.length == 0) {
            inputIp = getInputIp();
        } else {
            inputIp = args[0];
        }
        
        Boolean isValidIpAddress = validateIp(inputIp);
        if(isValidIpAddress) {
            System.out.println("Using IP: " + inputIp + " to find nearby places...");

            GeolocationHelper geolocationHelper = GeolocationHelper.getInstance();
            Location location = geolocationHelper.findLocation(inputIp);

            GeoNamesHelper geoNamesHelper = GeoNamesHelper.getInstance();
            location = geoNamesHelper.findNearbyLocation(location);
            
            System.out.println(location.getNearbyPlaceString());
        } else {
            System.err.print("Input IP '" + inputIp + "' is invalid.");            
        }
    }
    
    // Gets the user IP from the command line
    public static String getInputIp() {
        System.out.println("Please specify an Ip address to find nearby places:");
        Scanner scanner = new Scanner(System.in);
        String inputIp = scanner.nextLine();
        return inputIp;
    }
    
    
    // Confirm that the user entered a valid IPv4 or IPv6 IP.
    public static Boolean validateIp(String inputIp) {
        InetAddressValidator inetAddressValidator = InetAddressValidator.getInstance();
        Boolean isValidIpAddress = inetAddressValidator.isValid(inputIp);
        return isValidIpAddress;
    }

}
