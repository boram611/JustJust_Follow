package com.example.justfollow.db;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
        final static private String URL = "http://sangyu97.dothome.co.kr/Login.php";
        private Map<String, String> map;


        public LoginRequest(String userID, String userPassword, Response.Listener<String>listener){
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("user ID",userID);
            map.put("user Password",userPassword);
        }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
