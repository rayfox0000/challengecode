package com.example.hooch.medicsource;

/**
 * Created by hooch on 21/3/2018.
 */

public class Place {
    private String placeName;
    private String vicinity;
    private String openHour;
    private String placeId;
    private String rating;
    private String lat;
    private String log;

    public Place(String placeName, String vicinity, String openHour, String placeId, String rating, String lat, String log) {
        this.placeName = placeName;
        this.vicinity = vicinity;
        this.openHour = openHour;
        this.placeId = placeId;
        this.rating = rating;
        this.lat = lat;
        this.log = log;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getOpenHour() {
        return openHour;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getRating() {
        return rating;
    }

    public String getLat() {
        return lat;
    }

    public String getLog() {
        return log;
    }
}
