package com.example.listycitylab6;

import androidx.annotation.Nullable;

/**
 * This is a class that defines a City.
 *
 * It has 2 string data menbers of string type and 2 setters
 *
 * @author Moosa Hassan Alvi
 * @version 1.0.0
 */
public class City implements Comparable<City>{
    private String city;
    private String province;
    City(String city, String province){
        this.city = city;
        this.province = province;
    }
    /**
     * This is the getter of city attribute
     * @return the city name
     */
    String getCityName(){
        return this.city;
    }
    /**
     * This is the getter of the province attribute
     * @return the province
     */
    String getProvinceName(){
        return this.province;
    }
    /**
     *Overwriding the compareto function to allow coparism between cities
     * @param o a city object
     * @return 1 if it contains else 0
     */

    @Override
    public int compareTo(City o) {
        return this.city.compareTo(o.getCityName()); // this.city refers to the city name
    }

    /**
     *Overrides the equals function to define equality for city
     * @param obj and object which we cast to city
     * @return True if equals else False
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return Boolean.TRUE;
        City c = (City) obj;
        return this.city.equals(c.getCityName()) && this.province.equals(c.getProvinceName());
    }
}
