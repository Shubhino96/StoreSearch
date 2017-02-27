package com.example.shubhangi.storesearch;

/**
 * Created by shubhangi on 20-02-2017.
 */

public class Storelist {
    private String info;
    public Storelist(String inf)
    {
        info=inf;
    }
    public String getstart()
    {
        char c=info.charAt(0);
        String st=c+" ";
        return st;
    }
    public String getinform()
    {
        return info;
    }
}
