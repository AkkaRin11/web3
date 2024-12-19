package org.example.service;

public class Validation {
    public static boolean isHit(double x, double y, double r){

        if (x >= 0 && y >= 0 && x*2 + y < r){
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

//    public static boolean checkValid(double x, double y, double r) {
//        if (y > 5 || y < -3) {
//            return false;
//        }
//
//        boolean flagX = false;
//        for (double i = -2; i <= 2; i+=0.5){
//            if (i == x){
//                flagX = true;
//            }
//        }
//
//        if (!flagX){
//            return false;
//        }
//
//        boolean flagR = false;
//        for (double i = 1; i <= 4; i+=0.25){
//            if (i == r){
//                flagR = true;
//            }
//        }
//
//        return flagR;
//    }
}