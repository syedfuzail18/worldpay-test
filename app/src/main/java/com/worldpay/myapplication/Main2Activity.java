package com.worldpay.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.worldpay.Card;
import com.worldpay.CardValidationError;
import com.worldpay.HttpServerResponse;
import com.worldpay.ResponseCard;
import com.worldpay.ResponseError;
import com.worldpay.WorldPay;
import com.worldpay.WorldPayError;
import com.worldpay.WorldPayResponse;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button nxt = findViewById(R.id.btn);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        final WorldPay worldPay = WorldPay.getInstance();

        Card card = new Card();

        CardValidationError validate = card.setHolderName("fuzail").//
                setCardNumber("3528000700000000")//
                .setCvc("111").setExpiryYear("2020").setExpiryMonth("11")//
                .validate();
        AsyncTask<Void, Void, HttpServerResponse> createTokenAsyncTask = worldPay
                .createTokenAsyncTask(this, card, new WorldPayResponse() {

                    @Override
                    public void onSuccess(ResponseCard responseCard) {
                        Log.d("test", "onSuccess1: "+responseCard);
                        // DebugLogger.d("# onSuccess : " + responseCard);
                        //   finishActivity(RESULT_RESPONSE_CARD, EXTRA_RESPONSE_CARD, responseCard);
                    }

                    @Override
                    public void onResponseError(ResponseError responseError) {
                        Log.d("test", "onSuccess1: "+responseError);
                        //    DebugLogger.d("# onResponseError: " + responseError.getMessage());
                        //    finishActivity(RESULT_RESPONSE_ERROR, EXTRA_RESPONSE_ERROR, responseError);
                    }

                    @Override
                    public void onError(WorldPayError worldPayError) {
                        Log.d("test", "onSuccess1: "+worldPayError);
                        //  DebugLogger.d("# onError: " + worldPayError.getMessage());

                        //   finishActivity(RESULT_WORLDPAY_ERROR, EXTRA_RESPONSE_WORLDPAY_ERROR, worldPayError);
                    }

                });

        if (createTokenAsyncTask != null) {
            createTokenAsyncTask.execute();
        }
    }
}

