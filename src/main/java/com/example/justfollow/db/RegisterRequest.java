package com.example.justfollow.db;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
        final static private String URL = "http://sangyu97.dothome.co.kr/Register.php";
        private Map<String, String> map;


        public RegisterRequest(String userID, String userPassword, String userName, Response.Listener<String>listener){
            super(Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("user ID",userID);
            map.put("user Password",userPassword);
            map.put("user Name",userName);
        }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
