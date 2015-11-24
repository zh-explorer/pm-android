package com.example.explorer.en;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.explorer.en.decodeJson.decode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int SHOW_RESPONSE = 0;

    private Button sendRequest;

    private TextView responseText;

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    Data data = (Data) msg.obj;
                    String str = "PM: " + data.getPM() + "\n";
                    str += "Latitude: " + data.getLatitude() + "\n";
                    str += "Longitude" + data.getLongitude() + "\n";
                    responseText.setText(str);
            }
        }
    }  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendRequest = (Button) findViewById(R.id.send_response);
        responseText = (TextView) findViewById(R.id.response_text);

        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_response) {
            getWebData.sendHttpRequest("http://121.42.25.113:8080/?user=explorer", new HttpCallbackListenter() {

                @Override
                public void onFinish(String response) {
                    Data data = decode(response);
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = data;
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
















