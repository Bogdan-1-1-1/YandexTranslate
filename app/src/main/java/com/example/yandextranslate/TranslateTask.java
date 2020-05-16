package com.example.yandextranslate;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TranslateTask extends AsyncTask<Request, Void, Response> {
    final MainActivity activity;

    public TranslateTask(MainActivity activity) {
        this.activity = activity;
    }


    public Response requestToServer(Request req) {
        String URL_API = "https://translate.yandex.net/api/v1.5/tr.json/translate";
        Gson gson = new Gson();

        try {
            URL url = new URL(URL_API);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);

            OutputStream out = urlConnection.getOutputStream();
            out.write(req.toByteArray());

            InputStream in = urlConnection.getInputStream();
            Response res = gson.fromJson(new InputStreamReader(in), Response.class);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    protected Response doInBackground(Request...requests) {
        Request r = (Request) requests[0];
        return requestToServer(r);
    }

    @Override
    protected void onPostExecute(Response response) {
        if(response != null) {
            Log.d("text:", "translation succeed: "+response.toString());
            activity.showTranslation(response.toString());
        } else Log.d("text:", "translation failed");
    }

}
