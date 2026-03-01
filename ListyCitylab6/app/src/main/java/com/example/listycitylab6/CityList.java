package com.example.listycitylab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  This is a class that keeps a list of cities
 *
 * @author Moosa Hassan Alvi
 * @see City
 * @version 1.0.0
 */
public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     * @param city This is a candidate city to add
     *
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     * @return  Return the sorted list
     *
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }
    /**
     * This checks the list to see if a city is in it
     * @return  Return True if city in list otherwise false
     * @param city This is the city object to serch
     *
     */
    public Boolean hasCity(City city){
        return cities.contains(city);
    }
    /**
     *  This checks to see if a city is in list and deletes it
     * @param city the city object to delete
     * Throw exception is city not found
     **/
    public void delete(City city) {
        if (!cities.contains(city)) {
            throw new IllegalArgumentException("City not found in list");
        }
        cities.remove(city);
    }
    /**
     * This returns the number of cities in the list
     * returns The size of the city list
     */
    public int countCities() {
        return cities.size();
    }

}
