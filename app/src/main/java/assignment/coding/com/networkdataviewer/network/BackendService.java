package assignment.coding.com.networkdataviewer.network;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import assignment.coding.com.networkdataviewer.callbacks.DataNotificationCallback;
import assignment.coding.com.networkdataviewer.exceptions.DataFetchingException;

public class BackendService extends Service {

    // Binder given to clients
    private final IBinder mBinder = new ServiceBinder();

    private DataNotificationCallback dataNotificationCallback;

    public void setDataNotificationCallback(DataNotificationCallback dataNotificationCallback) {
        this.dataNotificationCallback = dataNotificationCallback;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class ServiceBinder extends Binder {
        BackendService getService() {
            // Return this instance of ServiceBinder so clients can call public methods
            return BackendService.this;
        }
    }

    /**
     * Method used to fetch data from service in background.
     *
     * @param requestUrl url.
     * @return response in form of string.
     * @throws IOException           input/output exception.
     * @throws DataFetchingException data fetching exp.
     */
    public String fetchDataFromUrl(String requestUrl) throws IOException, DataFetchingException {
        InputStream inputStream;

        HttpURLConnection urlConnection;

        /* forming th java.net.URL object */
        URL url = new URL(requestUrl);

        urlConnection = (HttpURLConnection) url.openConnection();

        /* for Get request */
        urlConnection.setRequestMethod("GET");
        /* optional request header */
        urlConnection.setRequestProperty("Content-Type", "application/json");

        /* optional request header */
        urlConnection.setRequestProperty("Accept", "application/json");
        /* optional request header */
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");


        int statusCode = urlConnection.getResponseCode();

        if (statusCode == HttpURLConnection.HTTP_OK) {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());

            String response = convertInputStreamToString(inputStream);
            if (response != null)
                dataNotificationCallback.onSuccess();
            return response;
        } else {
            throw new DataFetchingException("Failed to fetch data from url from :-" + requestUrl);
        }
    }

    /**
     * Method sed to convert input stream from service to string.
     *
     * @param inputStream inputStream.
     * @return response.
     * @throws IOException input output exp.
     */
    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        String result = "";
        if (null != inputStream) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            inputStream.close();
        }
        return result;
    }


    public class FetchJsonData extends AsyncTask<String, Integer, String> {

        private String response;

        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            try {
                response = fetchDataFromUrl(url);
            } catch (IOException | DataFetchingException e) {
                e.printStackTrace();
            }


            return response;
        }

        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(dataNotificationCallback!=null)
                dataNotificationCallback.onReceive(result);
        }
    }

}
