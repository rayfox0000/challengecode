package com.example.hooch.medicsource;

import android.widget.TextView;
/**
 * Created by hooch on 20/3/2018.
 */

public class PlaceHolder {
    private String medicName;
    private String operatingHour;
    private String rating;
    private String address;
    private String phoneNo;

    public PlaceHolder(){

    }

    public PlaceHolder(String medicName, String operatingHour, String rating, String address, String phoneNo) {
        this.medicName = medicName;
        this.operatingHour = operatingHour;
        this.rating = rating;
        this.address = address;
        this.phoneNo = phoneNo;
    }
}
