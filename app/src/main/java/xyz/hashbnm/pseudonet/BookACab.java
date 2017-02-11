package xyz.hashbnm.pseudonet;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BookACab extends AppCompatActivity {

    Button search;
    EditText origin,destination;
    TextView output;
    String outputText;
    private IntentFilter intentFilter;
    private String incomingDataContent;
    private String senderID,result;
    ProgressDialog p;
    private IncomingData data;

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
            if (incomingDataContent.contains("cab")||incomingDataContent.contains("Cab")) {
                p.hide();
                outputText = incomingDataContent;
//                outputText.
                outputText = str_piece(outputText,':',2);
                output.setText((outputText));
            }
        }
    };


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
        setContentView(R.layout.activity_book_acab);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
//        Toast.makeText(getApplicationContext(), "Listening for Incoming Messages", Toast.LENGTH_LONG).show();
        output = (TextView) findViewById(R.id.output);
        origin =(EditText) findViewById(R.id.origin);
        destination =(EditText) findViewById(R.id.destination);
        p = new ProgressDialog(this);
        search = ( Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                p.setMessage("Getting Directions");
//                p.show();
//                SmsManager sms = SmsManager.getDefault();
//                ArrayList<String> parts = sms.divideMessage("cab:"+origin+":"+destination);
//                sms.sendMultipartTextMessage("8872039507", null, parts, null, null);
//                output.setText(Html.fromHtml(""));

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

