package com.worldpay.myapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.worldpay.Card;
import com.worldpay.SaveAlternativePaymentMethodActivity;
import com.worldpay.SaveCardActivity;
import com.worldpay.WorldPay;

public class MainActivity extends AppCompatActivity {

    private String YOUR_CLIENT_KEY="T_C_33b7f794-1bb5-4aee-ba89-6081922b0a4b";
    private TextView click;
    private int SAVE_CARD_REQUEST_CODE=999;
    private int SAVE_APM_REQUEST_CODE=888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorldPay worldpay = WorldPay.getInstance();
        worldpay.setClientKey(YOUR_CLIENT_KEY);
        // decide whether you want to charge this card multiple times or only once
        worldpay.setReusable(true);
        // set validation type advanced or basic
        Card.setValidationType(Card.VALIDATION_TYPE_ADVANCED);
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                //SAVE_CARD_REQUEST_CODE a custom request code for that activity
                startActivityForResult(intent, SAVE_CARD_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        //SAVE_APM_REQUEST_CODE a custom request code for that activity
        startActivityForResult(intent, SAVE_APM_REQUEST_CODE);
    }
}
