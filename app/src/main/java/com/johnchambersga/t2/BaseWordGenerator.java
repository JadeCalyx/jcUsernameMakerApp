package com.johnchambersga.t2;

import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by john.chambers on 12/16/16.
 */

public class BaseWordGenerator {


    private String _word = "";
    private TextView _textView;
    private RequestQueue _requestQueue;
    private int wordsToReturn = 15;


    public BaseWordGenerator(TextView textView, RequestQueue requestQueue, String word) {
        _word = word;
        _textView = textView;
        _requestQueue = requestQueue;
    }

    public void go() {
        makeRequest();
    }

    private void makeRequest() {
        //RequestQueue rq = Volley.newRequestQueue(_this);

        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://api.datamuse.com/words?rel_jjb=" + _word,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        fillTable(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {

                    }
                });
        _requestQueue.add(sr);
    }

    private void fillTable(String response) {
        _textView.setText("");
        //_textView.setText(_word);

        JSONArray ja = null;
        try {
            ja = new JSONArray(response);
        }
        catch (Exception e) {
            int x =1;
        }

        if (ja.length() < 1) {
            _textView.append("Word not found, try a different word.");
            return;
        }

        int i = wordsToReturn;
        while ((i-- > 0) && (ja.length() > 0)) {
            int n = 0;
            if (ja.length() > 1) {
                Random r = new Random();
                n = r.nextInt(ja.length() - 1);
            }
            try {
                JSONObject currJsonWord = (JSONObject) ja.get(n);
                String currWord = currJsonWord.getString("word");
                ja.remove(n);
                List<String> wl = new ArrayList<String>();
                wl.add(_word);
                wl.add(currWord);

                WordGetter wg = new WordGetter(_textView, _requestQueue, wl, "b");
                wg.go();
            }
            catch (Exception e) {

            }

        }


    }


}
