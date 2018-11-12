package hurri.ucsc.edu.hurri;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makePhoneCall();
    }

    private void makePhoneCall() {
        String number = "9497356738";
        if (number.length() > 0) {
           if (ContextCompat.checkSelfPermission(MainActivity.this,
                   Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) { // phone call permission not yet granted
               ActivityCompat.requestPermissions(MainActivity.this,
                       new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL );
           }
           else { // make the phone call
               String dial = "tel:" + number;
               startActivity( new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
           }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
            else {
                Toast.makeText(this,"Permission denied :(", Toast.LENGTH_SHORT);
            }
        }
    }
}