package com.johnchambersga.t2;

import android.text.TextUtils;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by john.chambers on 12/16/16.
 */

public class WordGetter {

    private List<String> _wordList;
    private TextView _textView;
    private RequestQueue _requestQueue;
    private String _countdown;
    private char _nextAction;
    private WordFilter _wordFilter;

    public WordGetter(TextView textView, RequestQueue requestQueue, List<String> wordList, String countdown) {
        _wordList = wordList;
        _textView = textView;
        _requestQueue = requestQueue;
        _countdown = countdown;
        _wordFilter = new WordFilter();
    }

    public void go() {
        makeIt();
    }

    private void makeIt() {
        if (_countdown.length() == 0) {
            cleanWordList();
            Collections.reverse(_wordList);
            String outString = TextUtils.join(" ", _wordList);
            _textView.append(outString.trim() + "\n");
        }
        else {
            _nextAction = _countdown.charAt(0);
            _countdown = _countdown.replaceFirst(String.valueOf(_nextAction), "");
            makeRequest();
        }

    }

    private void cleanWordList() {
        for (int i=0; i < _wordList.size(); i++) {
            if (_wordFilter.isBad(_wordList.get(i))) _wordList.set(i, "****");
        }
    }

    private void makeRequest() {

        String direction = "b";
        String wordToUse = _wordList.get(_wordList.size() - 1);
        if (_nextAction == 'a') {
            direction = "a";
            wordToUse = _wordList.get(0);
        }
        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://api.datamuse.com/words?rel_jj" + direction + "=" + wordToUse,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        fillTable(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String y = "error";
                    }
                });
        _requestQueue.add(sr);
    }


    private void fillTable(String response) {
        JSONArray ja = null;
        String currWord = "";
        try {
            ja = new JSONArray(response);
            int n = 0;
            if (ja.length() > 1) {
                Random r = new Random();
                n = r.nextInt(ja.length() - 1);
            }
            JSONObject currJsonWord = (JSONObject) ja.get(n);
            currWord = currJsonWord.getString("word");
        }
        catch (Exception e) {}
        if (_nextAction == 'a') {
            _wordList.add(0, currWord);
        }
        else {
            _wordList.add(currWord);
        }
        makeIt();
    }
}
