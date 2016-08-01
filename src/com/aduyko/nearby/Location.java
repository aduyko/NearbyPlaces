/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aduyko.nearby;
import org.w3c.dom.*;

/**
 *
 * @author Andy
 */
public class Location {
    private String ipAddress = null;
    private String latitude = null;
    private String longitude = null;
    private String locationName = null;
    private String locationLatitude = null;
    private String locationLongitude = null;
    private String locationDistance = null;

    public Location(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    // Format a string depending on if a nearby place has been found for this
    // location.
    public String getNearbyPlaceString() {
        if (locationName != null) {
            return String.format(
                    "A place near you is '%s', " +
                    "%s kilometers away, at " +
                    "%s, %s (Lat,Long)", 
                    locationName,
                    locationDistance,
                    locationLatitude,
                    locationLongitude
            );
        } else {
            return "There is no nearby place, sorry :(";
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getLocationDistance() {
        return locationDistance;
    }

    public void setLocationDistance(String locationDistance) {
        this.locationDistance = locationDistance;
    }
    
}
