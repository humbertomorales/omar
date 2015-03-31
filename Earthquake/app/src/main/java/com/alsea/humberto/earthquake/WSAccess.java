package com.alsea.humberto.earthquake;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by humberto on 3/30/15.
 */
public class WSAccess {

    String json;


    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(replyString);

        // trim the whitespaces
        return replyString.trim();
    }

    public ArrayList parseQuakeParse(final String response) {

        ArrayList temp = new ArrayList();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("features")) {

                JSONArray jsonArray = jsonObject.getJSONArray("features");



                for (int i = 0; i < jsonArray.length(); i++) {
                    temp.add(new Info(
                            jsonArray.getJSONObject(i).getJSONObject("properties").getString("mag"),
                            jsonArray.getJSONObject(i).getJSONObject("properties").getString("place"),
                            jsonArray.getJSONObject(i).getJSONObject("properties").getString("title"),
                            jsonArray.getJSONObject(i)
                                    .getJSONObject("geometry").getString("coordinates")
                            ));
                }
                  //  GooglePlace poi = new GooglePlace();
                    //if (jsonArray.getJSONObject(i).has("name")) {
                //    poi.setName(jsonArray.getJSONObject(i).optString("name"));
                //      poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));
////
                //                    if (jsonArray.getJSONObject(i).has("opening_hours")) {
                //          if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                //              if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now").equals("true")) {
                //                  poi.setOpenNow("YES");
                //              } else {
                //                  poi.setOpenNow("NO");
                //               }
            //          }
                //      } else {
                //          poi.setOpenNow("Not Known");
                //           }
            //if (jsonArray.getJSONObject(i).has("types")) {
                            //JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");

                            //for (int j = 0; j < typesArray.length(); j++) {
                                //poi.setCategory(typesArray.getString(j) + ", " + poi.getCategory());
                                //}
                            //          }
            //          }
        //                  temp.add(poi);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }


}
