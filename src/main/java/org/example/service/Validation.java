package org.example.service;

public class Validation {
    public static boolean isHit(double x, double y, double r){

        if (x >= 0 && y >= 0 && x + y*2 < r){
            return true;
        }

        if (x >= 0 && y <= 0 && x * x + y * y <= r * r){
            return true;
        }

        if (x <= 0 && y <= 0 && x >= -r/2 && y >= -r){
            return true;
        }

        return false;

    }

    public static boolean checkValidNumber(String str) {
        return str.matches("^[-+]?[0-9]*[.][0-9]+$") || str.matches("^[-+]?[0-9]+$");
    }
}