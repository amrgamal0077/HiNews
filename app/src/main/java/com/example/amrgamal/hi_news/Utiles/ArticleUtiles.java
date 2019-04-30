package com.example.amrgamal.hi_news.Utiles;

import android.util.Log;

import com.example.amrgamal.hi_news.Data.Articles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by AmrGamal on 25/04/2019.
 */

public class ArticleUtiles {
    private static URL createUrl(String Url)
    {
        URL url=null;
        try {
            url = new URL(Url);
        } catch (MalformedURLException e) {
            Log.e(ArticleUtiles.class.getName(), "Problem building the URL ", e);
        }
        return url;
    }
    private static ArrayList<Articles> extraxtMovies(String json){
        ArrayList<Articles>articles= new ArrayList<>();

        try {

            JSONObject result=new JSONObject(json);
            JSONArray article_array=result.getJSONArray("articles");
                for (int j=0;j<article_array.length();j++){
                    Articles articles1= new Articles();
                    JSONObject object = (JSONObject) article_array.get(j);
                    articles1.title=object.getString("title");
                    articles1.author=object.getString("author");
                    articles1.photo_url=object.getString("urlToImage");
                    articles1.description=object.getString("description");
                    articles.add(articles1);
                }

            } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return articles;

    }
    private static String makeHttpRequest(URL url) throws IOException {
        String JsonResponse ="";
        if (url==null)
            return null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream=null;
        try {
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode()==200)
            {
                inputStream=httpURLConnection.getInputStream();
                JsonResponse=readFromStream(inputStream);
            }
            else {
                Log.e(ArticleUtiles.class.getName(), "Error response code: " +httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }

        return JsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static ArrayList<Articles> fetchdata(String Url)
    {

        URL url =createUrl(Url);
        String jsonData=null;
        try {
            jsonData=makeHttpRequest(url);
        } catch (IOException e) {
            // Log.e(MoviesUtils.class.getName(), "Problem making the HTTP request.", e);
        }

        return extraxtMovies(jsonData);
    }
}
