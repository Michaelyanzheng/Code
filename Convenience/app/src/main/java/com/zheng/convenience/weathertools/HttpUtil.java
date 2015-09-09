package com.zheng.convenience.weathertools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by michael on 2015/9/8.
 */
public class HttpUtil {

    public static void sendHttpRequest(final String address,
                                       final SuccessCallback successCallback,
                                       final FailCallback failCallback){


        new Thread(new Runnable(){

            public void run() {
                HttpURLConnection connection = null;
                try{
                    Log.d("HttpUtil", "sendHttpRequest  try ....");
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }

                    if(successCallback != null){
                        successCallback.onSuccess(response.toString());
                    }
                }catch(Exception e){
                    if(failCallback != null){
                        failCallback.onFail();
                    }
                }finally{
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }}).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                HttpURLConnection connection = null;
//
//                InputStream inputStream = null;
//                InputStreamReader inputStreamReader = null;
//                BufferedReader bufferedReader = null;
//
//                try {
//                    URL url = new URL(address);
//
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(5000);
//                    connection.setReadTimeout(5000);
//
//                    inputStream = connection.getInputStream();
//                    inputStreamReader = new InputStreamReader(inputStream);
//                    bufferedReader = new BufferedReader(inputStreamReader);
//
//                    String line;
//                    StringBuilder resultStringBuilder = new StringBuilder();
//                    while((line = bufferedReader.readLine()) != null){
//                        resultStringBuilder.append(line);
//                    }
//
//                    if (successCallback != null){
//                        successCallback.onSuccess(resultStringBuilder.toString());
//                    }
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                    if (failCallback != null){
//
//                        failCallback.onFail();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    if (failCallback != null){
//
//                        failCallback.onFail();
//                    }
//                }finally {
//
//                    if (bufferedReader != null){
//                        try {
//                            bufferedReader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (inputStreamReader != null){
//                        try {
//                            inputStreamReader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (inputStream != null){
//                        try {
//                            inputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (connection != null){
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();

    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
