package xyz.hashbnm.pseudonet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by almukthar on 27/3/16.
 */
public class SMSListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            String msgBody = "";
            if (bundle != null){
                //---retrieve the SMS message received---
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                       if (msg_from.trim().length() == 13 || msg_from.trim().length() == 12 || msg_from.trim().length() == 10)  {
                            msgBody += msgs[i].getMessageBody();

                            //registering the broadcast
                            Intent broadcastIntent = new Intent();
                            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
                            broadcastIntent.putExtra("sms", msgBody);
                            broadcastIntent.putExtra("senderID",msg_from);
                            context.sendBroadcast(broadcastIntent);
                           Toast.makeText(context, "aaya", Toast.LENGTH_LONG).show();
//                           SmsManager sms = SmsManager.getDefault();
//                           ArrayList<String> parts = sms.divideMessage("k ");
//                           sms.sendMultipartTextMessage(msg_from, null, parts, null, null);
                            Log.e("aaya","asd");
                       }
                        //if it throws error / doesnt seems working remove filtering sender no#
                    }

                }
                catch(Exception e)
                {
                    Log.d("Exception caught", e.getMessage());
                }
            }
        }
    }
}
