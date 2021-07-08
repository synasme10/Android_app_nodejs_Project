package model;

public class LatitudeLongitude {
    private double latitude;
    private double longitude;
    private String marker;


    public LatitudeLongitude(double latitude, double longitude, String marker) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.marker = marker;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
