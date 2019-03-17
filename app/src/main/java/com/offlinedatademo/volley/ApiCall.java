package com.offlinedatademo.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.offlinedatademo.Interface.InstanceListner;


import org.json.JSONObject;


public class ApiCall {

    private static ApiCall instance = null;
    private JsonObjectRequest jsonObjectRequest;
    private int timeout = 20000;
    Context context;


    private ApiCall(Context context) {
        this.context = context;
    }

    public static synchronized ApiCall getInstance(Context context) {
        if (null == instance) {
            instance = new ApiCall(context);
        }
        return instance;
    }


    public static synchronized ApiCall getInstance() {
        if (null == instance) {
            throw new IllegalStateException(ApiCall.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    //0 get, 1 post
    public synchronized void callingApi(int methodType, final String url, final JSONObject jsonObject, final InstanceListner<String> listner) {
        Log.d("Response", url + " >>  >" + jsonObject.toString());
        RequestQueue queue = Volley.newRequestQueue(context);
        jsonObjectRequest = new JsonObjectRequest(methodType, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response>>>", response.toString());
                listner.getResult(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error...");
                //  Toast.makeText(context, "No record found", Toast.LENGTH_SHORT).show();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(timeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);
    }

}
