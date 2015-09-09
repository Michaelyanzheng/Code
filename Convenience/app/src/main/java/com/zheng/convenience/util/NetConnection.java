package com.zheng.convenience.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by michael on 2015/8/28.
 */
public class NetConnection {

    public NetConnection(final String url,
                         final HttpMethod method,
                         final SuccessCallback successCallback,
                         final FailCallback failCallback,
                         final String ...kvs){

        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {

                String getUrlStr;

                if (kvs != null){
                    StringBuilder paramsStr = new StringBuilder();
                    paramsStr.append("?");

                    for (int i = 0; i < kvs.length; i+=2){

                        paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                    }

                    getUrlStr = url.concat(paramsStr.toString());

                }else{

                    getUrlStr = url;
                }

                Log.d(Config.TAG,getUrlStr);

                try {

                    HttpURLConnection urlConnection = null;

                    URL url = new URL(getUrlStr);

                    switch (method){

                        case POST:

                            urlConnection = (HttpURLConnection) url.openConnection();

                            break;

                        default:
                            urlConnection = (HttpURLConnection) url.openConnection();
                            break;
                    }

                    urlConnection.addRequestProperty("encoding", "UTF-8");
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);

                    urlConnection.setDoInput(true);

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line = "";
                    StringBuilder resultStringBuilder = new StringBuilder();

                    while ((line = bufferedReader.readLine()) != null){
                        resultStringBuilder.append(line);
                    }

                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    urlConnection.disconnect();

                    Log.d(Config.TAG,resultStringBuilder.toString());

                    return resultStringBuilder.toString();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {

                if (result != null){

                    if (successCallback != null){

                        successCallback.onSuccess(result);

                    }else{

                        if (failCallback != null){

                            failCallback.onFail();
                        }
                    }
                }else{

                    if (failCallback != null){

                        failCallback.onFail();
                    }
                }

                super.onPostExecute(result);
            }

        }.execute();
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
