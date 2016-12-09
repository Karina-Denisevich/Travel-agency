package com.github.karina_denisevich.travel_agency.services.locale.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TranslatorUtil {

    public String translate(String from, String into, String text){



         try {
//             String result = URLEncoder.encode("Hello my dear students", "UTF-8");
//
//             URL url = new URL("http://translate.googleapis.com/translate_a/single?client=gtx&sl=" + "en" + "&tl="
//                    + "ru" + "&dt=t&q=" + result + "&ie=UTF-8&oe=UTF-8");
//
//            URLConnection uc = url.openConnection();
//            uc.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            result = br.readLine();


            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                    "key=trnsl.1.1.20161208T210352Z.1a5b09010dcd1a00.a8c73d2729c9bf96620d1163f843be2bb5bb766d" +
                    "&text=Hello world" +
                    "&lang=ru");
            URLConnection uc = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String result = br.readLine();
            System.out.println("*******  " + result);

            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
            result = jsonObject.get("text").getAsString();

            System.out.println("______________" + result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
