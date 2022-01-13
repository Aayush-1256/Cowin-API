package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {

    public static String[][] data( JSONArray arr , String age,String vaccine,String dose)
    {
        List<Hospitals> list = new ArrayList<>();

        for(int i=0;i<arr.size();i++)
        {
            JSONObject obj = (JSONObject) arr.get(i);

//            System.out.print("State ID -> "+obj.get("state_id")+"\n State Code -> "+obj.get("state_name"));
//////            System.out.println();
//            data[i][0] = String.valueOf(obj.get("name"));
//            data[i][1] = String.valueOf(obj.get("address"));
//            data[i][2] = String.valueOf(obj.get("available_capacity"));
//            data[i][3] = String.valueOf(obj.get("available_capacity_dose1"));
//            data[i][4] = String.valueOf(obj.get("available_capacity_dose2"));
//            data[i][5] = String.valueOf(obj.get("min_age_limit"));
//            data[i][6] = String.valueOf(obj.get("vaccine"));

//            if(all&&!String.valueOf(obj.get("available_capacity")).equals("0")&&String.valueOf(obj.get("vaccine")).equals(vaccine))
//            {list.add(new Hospitals(String.valueOf(obj.get("name")),
//            String.valueOf(obj.get("address")),
//            String.valueOf(obj.get("available_capacity")),
//            String.valueOf(obj.get("available_capacity_dose1")),
//            String.valueOf(obj.get("available_capacity_dose2")),
//            String.valueOf(obj.get("min_age_limit")),
//            String.valueOf(obj.get("vaccine"))));}

            if(String.valueOf(obj.get("min_age_limit")).equals(age)&&!String.valueOf(obj.get("available_capacity")).equals("0")&&String.valueOf(obj.get("vaccine")).equals(vaccine)
                    &&!String.valueOf(obj.get(dose)).equals("0"))
            {
                list.add(new Hospitals(String.valueOf(obj.get("name")),
                        String.valueOf(obj.get("address")),
                        String.valueOf(obj.get("available_capacity")),
                        String.valueOf(obj.get("available_capacity_dose1")),
                        String.valueOf(obj.get("available_capacity_dose2")),
                        String.valueOf(obj.get("min_age_limit")),
                        String.valueOf(obj.get("vaccine"))));
            }

        }


        Collections.sort(list,(a, b)->a.name.compareTo(b.name));
        String[][] data = new String[list.size()][7];
        for(int i=0;i<list.size();i++)
        {

            data[i][0] = String.valueOf(list.get(i).name);
            data[i][1] = String.valueOf(list.get(i).address);
            data[i][2] = String.valueOf(list.get(i).available_capacity);
            data[i][3] = String.valueOf(list.get(i).available_capacity_dose1);
            data[i][4] = String.valueOf(list.get(i).available_capacity_dose2);
            data[i][5] = String.valueOf(list.get(i).min_age_limt);
            data[i][6] = String.valueOf(list.get(i).vaccine);

        }


return data;
    }


}
