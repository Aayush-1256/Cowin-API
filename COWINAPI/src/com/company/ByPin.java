package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class ByPin {

    public static String[][] bypin(String pincode,String date,String age,String vaccine,String dose) throws IOException, ParseException {
        URL url = new URL("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+date);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.setRequestMethod("GET");
        con.connect();

        System.out.println(con.getResponseCode() + " " + con.getResponseMessage());

        Scanner sc = new Scanner(con.getInputStream());
        String inline = "";
        while (sc.hasNext()) {
            inline += sc.nextLine();
        }

//        System.out.println("\nJSON data in string format");
//        System.out.println(inline);

        JSONParser parse = new JSONParser();
        JSONObject json = (JSONObject) parse.parse(inline);

        JSONArray arr = (JSONArray) json.get("sessions");

        return Data.data(arr,age,vaccine,dose);
    }


}
