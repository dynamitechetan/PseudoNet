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
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.GONE;

public class ActionsDO extends AppCompatActivity implements View.OnClickListener{
    Button search;
    EditText id;
    TextView output,action;
    String outputText,types;
    private IntentFilter intentFilter;
    private String incomingDataContent;
    private String senderID,result;
    ProgressDialog p;
    String type;

    private IncomingData data;
    ImageView enable_backups, disable_backups, reboot, power_cycle, shutdown, power_off, power_on, password_reset;
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
            if (incomingDataContent.contains("deletedroplet")||incomingDataContent.contains("deletedroplet")) {
                p.hide();
                id.setVisibility(GONE);
//                destination.setVisibility(GONE);
                search.setVisibility(GONE);
//                date.setVisibility(GONE);
                outputText = incomingDataContent;
                outputText = (str_piece(outputText,':',2));
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
        setContentView(R.layout.activity_actions_do);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
        type = getIntent().getStringExtra("type");

//        Toast.makeText(getApplicationContext(), "Listening for Incoming Messages", Toast.LENGTH_LONG).show();
        output = (TextView) findViewById(R.id.output);
        action = (TextView) findViewById(R.id.action);
        id =(EditText) findViewById(R.id.id);
//        destination =(EditText) findViewById(destination);

        enable_backups=(ImageView) findViewById(R.id.enable_backups);
        disable_backups=(ImageView) findViewById(R.id.disable_backups);
        reboot=(ImageView) findViewById(R.id.reboot);
        power_cycle=(ImageView) findViewById(R.id.power_cycle);
        shutdown=(ImageView) findViewById(R.id.shutdown);
        power_off=(ImageView) findViewById(R.id.power_off);
        power_on=(ImageView) findViewById(R.id.power_on);
        password_reset=(ImageView) findViewById(R.id.password_reset);



        p = new ProgressDialog(this);
        search = ( Button) findViewById(R.id.search);

//        date = ( EditText) findViewById(date);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                p.setMessage("Getting Directions");
//                p.show();
//                SmsManager sms = SmsManager.getDefault();
//                ArrayList<String> parts = sms.divideMessage("action:"+id.getText().toString()+":"+types);
//                sms.sendMultipartTextMessage("8872039507", null, parts, null, null);

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
//    "enable_backups", "disable_backups", "reboot", "power_cycle", "shutdown", "power_off", "power_on", "password_reset",
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.enable_backups):
                types = "enable_backups";
                break;
            case (R.id.disable_backups):
                types =  "disable_backups";
                break;
            case (R.id.power_cycle):

                types = "power_cycle";

                break;
            case (R.id.reboot):
                types = "reboot";
                break;
            case (R.id.power_off):
                types =  "power_off";

                break;
            case (R.id.power_on):
                types =  "power_on";
                break;
            case (R.id.password_reset):
                types = "password_reset";

                break;
            case (R.id.shutdown):
                types = "shutdown" ;
                break;

        }
        action.setText(types);
    }
}



