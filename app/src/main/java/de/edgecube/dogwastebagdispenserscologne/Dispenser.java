package de.edgecube.dogwastebagdispenserscologne;

/**
 * Created by boring_wozniak on 12/10/16.
 */

public class Dispenser {
    private String mId;
    private String mArea;
    private String mStreet;
    private String mZipCode;
    private String mCity;
    private double mLat;
    private double mLon;

    public Dispenser(String id, String area, String street, String zipCode, String city, double lat, double lon) {
        this.mId = id;
        this.mArea = area;
        this.mStreet = street;
        this.mZipCode = zipCode;
        this.mCity = city;
        this.mLat = lat;
        this.mLon = lon;
    }

    public String getId() {
        return mId;
    }

    public String getArea() {
        return mArea;
    }

    public String getStreet() {
        return mStreet;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public String getCity() {
        return mCity;
    }

    public double getLat() {
        return mLat;
    }

    public double getLon() {
        return mLon;
    }
}