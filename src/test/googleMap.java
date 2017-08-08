package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.print.attribute.standard.JobImpressionsSupported;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

public class googleMap {

    public static void main(String[] args) throws IOException, JSONException {
        String preUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        String endUrl = "&sensor=false&language=zh-TW&key=AIzaSyBzbntmAuGW16US8FK_QIoDNXOPlspRjNw";
        URL url = null;
        url = new URL(preUrl + "桃園市楊梅區楊湖路三段" + endUrl);
        URLConnection urlConnection = url.openConnection();
        InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String read;
        while ((read = bufferedReader.readLine()) != null) {
            stringBuffer.append(read);
        }
        JSONObject jsonObject = new JSONObject(stringBuffer.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        System.out.println(jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng"));
        System.out.println(jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat"));
    }
}
