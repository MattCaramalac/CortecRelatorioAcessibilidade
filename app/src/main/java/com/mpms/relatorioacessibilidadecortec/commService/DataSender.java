package com.mpms.relatorioacessibilidadecortec.commService;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DataSender extends AsyncTask<String, Void, String> {

    TrustAllCerts trust = new TrustAllCerts();
    URL serverEndPoint;
    HttpsURLConnection serverConnection = null;

    {
        try {
            serverEndPoint = new URL("https://10.112.136.17:3000/relatorio/v1/relatorio");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";

        try {
            serverConnection = (HttpsURLConnection) serverEndPoint.openConnection();
            serverConnection.setHostnameVerifier(trust.DO_NOT_VERIFY);
            serverConnection.setRequestMethod("POST");
            serverConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            serverConnection.setDoOutput(true);


            DataOutputStream wr = new DataOutputStream(serverConnection.getOutputStream());
            wr.writeBytes("PostData=" + strings[0]);
            wr.flush();
            wr.close();

            InputStream in = serverConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        serverConnection.disconnect();
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
    }
}


