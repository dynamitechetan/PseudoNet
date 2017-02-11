package xyz.hashbnm.pseudonet;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private String incomingDataContent;
    private String senderID,result;
    String city,temprature,weatherstatus;
    EditText cityname;
    Button search;
    ProgressDialog p;
//    private List<IncomingData> dataList;
    private IncomingData data;
    TextView weather,temp;
    ImageView status;
    private BroadcastReceiver intentReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            incomingDataContent = intent.getExtras().getString("sms");
            senderID = intent.getExtras().getString("senderID");
            data = new IncomingData();
            data.setIncomingData(incomingDataContent);
            data.setSenderID(senderID);
            data.setStatus("Completed");
            checkChannel(incomingDataContent);
//            dataList.add(data);
//            listAdapter.notifyDataSetChanged();
            if (incomingDataContent.contains("weather")||incomingDataContent.contains("Weather")) {
                p.hide();
                city = ( str_piece(incomingDataContent, ':', 2));
                city =  ( str_piece(city, ',', 1));
                temprature =  ( str_piece(incomingDataContent, ':', 3));
                temprature = ( str_piece(temprature, ',', 1));
                weatherstatus =  ( str_piece(incomingDataContent, ':', 4));
                temp.setText(temprature);
                weather.setText(weatherstatus);

                if(weatherstatus.contains("Clear")||weatherstatus.contains("Sun")||weatherstatus.contains("clear")||weatherstatus.contains("sun")){
                    status.setImageResource(R.drawable.sunny);

                }
                else if(weatherstatus.contains("rain")||weatherstatus.contains("Rain")){
                    status.setImageResource(R.drawable.rainy);

                }
                else if(weatherstatus.contains("cloud")||weatherstatus.contains("Cloud")){
                    status.setImageResource(R.drawable.cloudy);

                }


//                weatherstatus = ( str_piece(weatherstatus, ',', 1));
            }
            Toast.makeText(getApplicationContext(), "hahaha", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    private void checkChannel(String incomingDataContent) {
        if (incomingDataContent.contains("weather")) {
            data.setChannel("weather");
        } else if (incomingDataContent.contains("wikipedia.org/wiki/")) {
            data.setChannel("Wikipedia");
        } else
            data.setChannel("Web");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherscren);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
        Toast.makeText(getApplicationContext(), "Listening for Incoming Messages", Toast.LENGTH_LONG).show();
        temp = (TextView) findViewById(R.id.temp);
        weather = (TextView) findViewById(R.id.weather);
        status = (ImageView) findViewById(R.id.status);
        cityname =(EditText) findViewById(R.id.city);
         p = new ProgressDialog(this);
        search = ( Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city = cityname.getText().toString();
                p.setMessage("Getting Weather");
                p.show();
                SmsManager sms = SmsManager.getDefault();
                ArrayList<String> parts = sms.divideMessage("weather:"+city);
                sms.sendMultipartTextMessage("8872039507", null, parts, null, null);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        registerReceiver(intentReciever, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(intentReciever);
        super.onPause();
    }




    private static String str_piece(String str, char separator, int index) {
        String str_result = "";
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == separator) {
                count++;
                if(count == index) {
                    break;
                }
            }
            else {
                if(count == index-1) {
                    str_result += str.charAt(i);
                }
            }
        }
        return str_result;
    }
}
