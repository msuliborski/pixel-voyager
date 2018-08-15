package com.sulient.pixelvoyager.functions;

public class Functions {

    public static String numberFormatOf (double number){
        String output;
        double million, billion, trillion, quadrillion, quintillion, sextillion, septillion, octillion, nonillion, decillion, undecillion;
        million =           Math.pow(10, 6);
        billion =           Math.pow(10, 9);
        trillion =          Math.pow(10, 12);
        quadrillion =       Math.pow(10, 15);
        quintillion =       Math.pow(10, 18);
        sextillion =        Math.pow(10, 21);
        septillion =        Math.pow(10, 24);
        octillion =         Math.pow(10, 27);
        nonillion =         Math.pow(10, 30);
        decillion =         Math.pow(10, 33);
        undecillion =       Math.pow(10, 36);
        if (number < million )                                                  output = String.valueOf((int)number);
        else if ((number >= million)           && (number < billion))           output = String.format("%.2f", Math.round(number / Math.pow(10, 3))/Math.pow(10, 3)) + " Million";
        else if ((number >= billion)           && (number < trillion))          output = String.format("%.2f", Math.round(number / Math.pow(10, 6))/Math.pow(10, 3)) + " Billion";
        else if ((number >= trillion)          && (number < quadrillion))       output = String.format("%.2f", Math.round(number / Math.pow(10, 9))/Math.pow(10, 3)) + " Trillion";
        else if ((number >= quadrillion)       && (number < quintillion))       output = String.format("%.2f", Math.round(number / Math.pow(10, 12))/Math.pow(10, 3)) + " Quadrillion";
        else if ((number >= quintillion)       && (number < sextillion))        output = String.format("%.2f", Math.round(number / Math.pow(10, 15))/Math.pow(10, 3)) + " Quintillion";
        else if ((number >= sextillion)        && (number < septillion))        output = String.format("%.2f", Math.round(number / Math.pow(10, 18))/Math.pow(10, 3)) + " Sextillion";
        else if ((number >= septillion)        && (number < octillion))         output = String.format("%.2f", Math.round(number / Math.pow(10, 21))/Math.pow(10, 3)) + " Septillion";
        else if ((number >= octillion)         && (number < nonillion))         output = String.format("%.2f", Math.round(number / Math.pow(10, 24))/Math.pow(10, 3)) + " Octillion";
        else if ((number >= nonillion)         && (number < decillion))         output = String.format("%.2f", Math.round(number / Math.pow(10, 27))/Math.pow(10, 3)) + " Nonillion";
        else if ((number >= decillion)         && (number < undecillion))       output = String.format("%.2f", Math.round(number / Math.pow(10, 30))/Math.pow(10, 3)) + " Decillion";
        else if (number >= undecillion)                                         output = "Infinity";
        else output = "Something wrong, contact dev!";

        return output + " ";
    }


    public static String timeFormatOf(double seconds){
        String format="";

        if (seconds >= 86400) {
            if ((int)Math.floor(seconds/86400) < 10) format += "0" + (int)Math.floor(seconds/86400) + ":"; else format += (int)Math.floor(seconds/86400) + ":";
            seconds -= (int)Math.floor(seconds/86400) * 86400;}
        else {
            format += "00:";}

        if (seconds >= 3600) {
            if ((int)Math.floor(seconds/3600) < 10) format += "0" + (int)Math.floor(seconds/3600) + ":"; else format += (int)Math.floor(seconds/3600) + ":";
            seconds -= (int)Math.floor(seconds/3600) * 3600;}
        else {
            format += "00:";}

        if (seconds >= 60) {
            if ((int)Math.floor(seconds/60) < 10) format += "0" + (int)Math.floor(seconds/60) + ":"; else format += (int)Math.floor(seconds/60) + ":";
            seconds -= (int)Math.floor(seconds/60) * 60;}
        else {
            format += "00:";}

        if (seconds < 10)
            format += "0" + (int)seconds; else format += (int)seconds;

        return format;
    }
}
