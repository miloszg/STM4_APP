package pl.milosz.stm4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;
    private ImageView mapImage;
    private MainActivity activity;

    public ImageView getMapImage() {
        return mapImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        btnSend = (Button) findViewById(R.id.btnSend);
        mapImage = (ImageView) findViewById(R.id.mapImage);

        btnSend.setOnClickListener(v -> {
            try {
                RestConnector connector = new RestConnector(getApiEndpoint(), activity);
                connector.execute();
            } catch (MalformedURLException e) {
                return;
            }
        });
    }

    private String getApiEndpoint() {
        EditText etEndpoint = (EditText) findViewById(R.id.etEndpoint);
        EditText etXLU = (EditText) findViewById(R.id.etXLU);
        EditText etYLU = (EditText) findViewById(R.id.etYLU);
        EditText etXRL = (EditText) findViewById(R.id.etXRL);
        EditText etYRL = (EditText) findViewById(R.id.etYRL);

        return etEndpoint.getText().toString();
//        return new StringBuilder(etEndpoint.getText().toString()).append("?")
//                .append("xLU=").append(etXLU.getText().toString()).append("&")
//                .append("yLU=").append(etYLU.getText().toString()).append("&")
//                .append("xRL=").append(etXRL.getText().toString()).append("&")
//                .append("yRL=").append(etYRL.getText().toString())
//                .toString();
    }
}