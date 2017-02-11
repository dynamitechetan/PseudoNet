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
import android.widget.TextView;

public class GetAllDroplets extends AppCompatActivity {
//    Button search;
//    EditText what;
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
//            checkChannel(incomingDataContent);
//            dataList.add(data);
//            listAdapter.notifyDataSetChanged();
            if (incomingDataContent.contains("getdroplets")||incomingDataContent.contains("getdroplets")) {
                p.hide();
                outputText = incomingDataContent;
//
                outputText = ( str_piece(outputText, ':', 2));
                output.setText(outputText);
            }
//            Toast.makeText(getApplicationContext(), "hahaha", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_droplets);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
//        Toast.makeText(getApplicationContext(), "Listening for Incoming Messages", Toast.LENGTH_LONG).show();
        output = (TextView) findViewById(R.id.output);
//        what =(EditText) findViewById(R.id.what);
        p = new ProgressDialog(this);
//        search = ( Button) findViewById(R.id.search);
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });




//        p.setMessage("Getting News");
//        p.show();
//        SmsManager sms = SmsManager.getDefault();
//        ArrayList<String> parts = sms.divideMessage("getdroplets");
//        sms.sendMultipartTextMessage("8872039507", null, parts, null, null);

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

