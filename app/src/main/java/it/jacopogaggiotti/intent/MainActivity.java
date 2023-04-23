package it.jacopogaggiotti.intent;

import static android.Manifest.permission.SEND_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final String[] permissions = {SEND_SMS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this,SEND_SMS) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(permissions,0);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        View btn1 = findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms:3534438929"));
                intent.putExtra("sms_body","Ciao Jacopo");

                if(intent.resolveActivity(getPackageManager()) != null) {
                    if(ContextCompat.checkSelfPermission(getApplicationContext(),SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    } else {
                        requestPermissions(permissions,1);
                    }
                }
            }
        });
    }
}