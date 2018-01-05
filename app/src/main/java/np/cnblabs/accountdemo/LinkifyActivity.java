package np.cnblabs.accountdemo;

import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sanjogstha on 12/21/17.
 * CNB LABS
 * sanjogshrestha.nepal@gmail.com
 */

public class LinkifyActivity extends AppCompatActivity {
    @BindView(R.id.linkifyTV)
    TextView linkifyTV;
    @BindView(R.id.phoneBtn)
    Button phoneBtn;
    @BindView(R.id.sendSmsBtn)
    Button sendSmsBtn;

    private static final int PhoneCode = 777;
    private static final int SMSCode = 888;
    private Intent callPhoneIntent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkify);
        ButterKnife.bind(this);

        callPhoneIntent = new Intent(Intent.ACTION_CALL)
                .setData(Uri.parse("tel: " + phoneBtn.getText().toString()));

        Linkify.addLinks(linkifyTV, Linkify.ALL);
    }

    public void callPhone(View view) {
        if (ActivityCompat.checkSelfPermission(this, permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission.CALL_PHONE}, PhoneCode);
            return;
        }
        callPhone(callPhoneIntent);
    }

    private void callPhone(Intent callPhoneIntent) {
        startActivity(callPhoneIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PhoneCode:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    callPhone(callPhoneIntent);
                break;

            case SMSCode:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    sendSms();
                break;
        }
    }

    public void sendMail(View view) {
    }

    public void sendSMS(View view) {
        if(ActivityCompat.checkSelfPermission(this, permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                        permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission.SEND_SMS,
                    permission.READ_PHONE_STATE}, SMSCode);
            return;
        }
        sendSms();
    }

    private void sendSms() {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", "Testing SMS");
        sendIntent.putExtra("address"  , sendSmsBtn.getText().toString());
        sendIntent.setType("vnd.android-dir/mms-sms");
        sendIntent.setData(Uri.parse("smsto:"));
        startActivity(sendIntent);

       /* SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sendSmsBtn.getText().toString(), null, "Testing SMS", null , null);*/
    }
}
