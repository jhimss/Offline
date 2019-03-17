package com.offlinedatademo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.offlinedatademo.Interface.InstanceListner;
import com.offlinedatademo.room.DatabaseClient;
import com.offlinedatademo.room.Task;
import com.offlinedatademo.volley.ApiCall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    List<Task> ailment = new ArrayList<>();
    Task task;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getAilment();
    }

    private void getAilment() {
        JSONObject jsonObject = new JSONObject();
        ApiCall.getInstance().callingApi(0, "https://stercodigitex.com/medigence/api/customer/getailment", jsonObject, new InstanceListner<String>() {
            @Override
            public void getResult(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String status = json.getString("status");
                    String msg = json.getString("message");
                    if (status.equalsIgnoreCase("true")) {
                        JSONArray jsonArray = json.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            task = new Task();
                            task.setName(jsonObj.optString("name"));
                            task.setAilment_id(jsonObj.optString("id"));
                            ailment.add(task);

                            class SaveTask extends AsyncTask<Void, Void, Void> {

                                @Override
                                protected Void doInBackground(Void... voids) {

                                    //adding to database
                                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                            .taskDao()
                                            .insert(task);
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    super.onPostExecute(aVoid);

                                }
                            }
                            SaveTask st = new SaveTask();
                            st.execute();

                        }

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
