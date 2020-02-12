package com.dmdddm.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Details extends AppCompatActivity implements View.OnClickListener{
//    初始化
    private Button mBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();
         mBuy= findViewById(R.id.buy);
        mBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intentBuy = new Intent(this,OrderPage.class);
        switch (view.getId()){
            case R.id.buy:
                startActivity(intentBuy);
                break;
            default:break;
        }
    }
}
