package pl.milosz.stm4;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestConnector extends AsyncTask<Void, Void, Void> {

    public static final int STATUS_OK = 200;
    public static final String REQUEST_METHOD_GET = "GET";
    private final URL apiEndpoint;
    private final MainActivity activity;

    public RestConnector(String apiEndpoint, MainActivity activity) throws MalformedURLException {
        this.apiEndpoint = new URL(apiEndpoint);
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) apiEndpoint.openConnection();
            connection.setRequestProperty("User-Agent", "map-svc");
            connection.setRequestMethod(REQUEST_METHOD_GET);
            if (connection.getResponseCode() == STATUS_OK) {
                try (InputStream in = connection.getInputStream()) {
                    final Drawable d = Drawable.createFromStream(in, null);
                    activity.runOnUiThread(() -> activity.getMapImage().setImageDrawable(d));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                Log.i("LOG",String.valueOf(connection.getResponseCode()));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }
}