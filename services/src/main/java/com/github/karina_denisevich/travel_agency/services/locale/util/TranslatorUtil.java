package com.github.karina_denisevich.travel_agency.services.locale.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TranslatorUtil {

    private static final String API_FILE_NAME = "services\\src\\main\\resources\\api.properties";
    private String key;

    public String translate(String langFrom, String langTo, String text) {
        this.key = new PropertyFileUtil().getValue("key", API_FILE_NAME);
        if (StringUtils.isEmpty(langFrom)) {
            langFrom = getLanguage(text);
        }
        String languagePair = !StringUtils.isEmpty(langFrom) ?
                langFrom.concat("-").concat(langTo) : langTo;
        try {
            String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                    "key=" + key +
                    "&text=" + text +
                    "&lang=" + languagePair;
            return getResult(url, "text");

        } catch (IOException e) {
            return text;
        }
    }

    private String getLanguage(String text) {
        try {
            String url = "https://translate.yandex.net/api/v1.5/tr.json/detect?" +
                    "key=" + key +
                    "&text=" + text +
                    "&hint=en,ru,de";
            return getResult(url, "lang");
        } catch (IOException e) {
            return text;
        }
    }

    private String getResult(String urlValue, String param) throws IOException {

        // google translate
//            String result = URLEncoder.encode("Hello my dear students", "UTF-8");
//            URL url = new URL("http://translate.googleapis.com/translate_a/single?client=gtx&sl=" + "en" + "&tl="
//                    + "ru" + "&dt=t&q=" + result + "&ie=UTF-8&oe=UTF-8");
//            URLConnection uc = url.openConnection();
//            uc.setRequestProperty("User-Agent", "Mozilla/5.0");
//            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            result = br.readLine();


        URL url = new URL(urlValue);
        URLConnection uc = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String result = br.readLine();

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        result = jsonObject.get(param).getAsString();

        return result;
    }
}
