package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class City {

    public static void getCity(String code, TreeMap<String,String> map) throws IOException, ParseException {
        URL url = new URL("https://cdn-api.co-vin.in/api/v2/admin/location/districts/"+code);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.setRequestMethod("GET");
        con.connect();

        System.out.println(con.getResponseCode()+" "+con.getResponseMessage());

        Scanner sc = new Scanner(con.getInputStream());
        String inline="";
        while(sc.hasNext())
        {
            inline+=sc.nextLine();
        }
//        System.out.println("\nJSON data in string format");
//        System.out.println(inline);

        JSONParser parse = new JSONParser();
        JSONObject json = (JSONObject) parse.parse(inline);

        JSONArray arr = (JSONArray) json.get("districts");

        for(int i=0;i<arr.size();i++)
        {
            JSONObject obj = (JSONObject) arr.get(i);

//            System.out.print("State ID -> "+obj.get("district_name"));
//            System.out.println();
            map.put(String.valueOf(obj.get("district_name")),String.valueOf(obj.get("district_id")));
//            map.put(String.valueOf(obj.get("state_name")),String.valueOf(obj.get("state_id")));
        }

        sc.close();

    }

}
