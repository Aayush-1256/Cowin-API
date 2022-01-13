package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MobileOTP {

    public static String sendOTP(String mobile) throws IOException, ParseException {

        String json = "{\n"+"\"mobile\" : \""+mobile+"\"\n}";

        System.out.println(json);

        URL url = new URL("https://cdn-api.co-vin.in/api/v2/auth/public/generateOTP");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();

        System.out.println(con.getResponseCode()+" "+con.getResponseMessage());

        Scanner sc = new Scanner(con.getInputStream());
        String inline = "";
        while (sc.hasNext()) {
            inline += sc.nextLine()+"\n";
        }

        System.out.println(inline);

        JSONParser parse = new JSONParser();
        JSONObject object = (JSONObject) parse.parse(inline);

        String str = inline.substring(10,inline.length()-3);

        System.out.println(str);


        return str;

//        confirmOTP(str);
    }

    public static String confirmOTP(String otp,String id) throws IOException {

        URL url = new URL("https://cdn-api.co-vin.in/api/v2/auth/public/confirmOTP");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.setRequestProperty("Content-Type", "application/json");

        String json = "{\n"+"\"otp\": \""+otp+"\",\n\"txnId\": \""+id+"\"\n}";
        System.out.println(json);
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();

        System.out.println(con.getResponseCode()+" "+con.getResponseMessage());

        Scanner sc = new Scanner(con.getInputStream());
        String inline = "";
        while (sc.hasNext()) {
            inline += sc.nextLine()+"\n";
        }

        System.out.println(inline);
        System.out.println(inline.substring(10,inline.length()-3));
        return inline.substring(10,inline.length()-3);
    }


    public static void Certificate(String bearer_token,String id) throws IOException {

        URL url = new URL("https://cdn-api.co-vin.in/api/v2/registration/certificate/public/download?beneficiary_reference_id="+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.setRequestProperty("Authorization","Bearer "+ bearer_token);
//        con.setRequestProperty("Host","cdn-api.co-vin.in");
        con.setRequestMethod("GET");
        con.connect();

        System.out.println(con.getResponseCode() + " " + con.getResponseMessage());

        try (BufferedInputStream in = new BufferedInputStream(con.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream("Certificate.pdf")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }

    }
}