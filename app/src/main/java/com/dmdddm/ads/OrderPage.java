package com.dmdddm.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderPage extends AppCompatActivity {
    //    数量
    private int quantity1;
    //  单价
    private float price = 12.81f;

    //初始化控件
    TextView textView ;
    TextView tQuantity;
    Button mAdd;
    Button mReduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        getSupportActionBar().hide();

        final Toast toast = Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT);

        textView = findViewById(R.id.sum);
        tQuantity = findViewById(R.id.quantity);
        mAdd = findViewById(R.id.quantityAdd);
        mReduce = findViewById(R.id.quantityReduce);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result =Integer.toString(++quantity1);
                tQuantity.setText(result);
                textView.setText(SumPrice(quantity1,price)+"元");
            }
        });
        mReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity1>0){
                    String result =Integer.toString(--quantity1);
                    tQuantity.setText(result);
                    textView.setText(SumPrice(quantity1,price)+"元");
                }
                else{
                    toast.show();
                }

            }
        });

//        //获取当前数量
        quantity1=Integer.parseInt(tQuantity.getText().toString());
        textView.setText(SumPrice(quantity1,price)+"元");

    }


    //合计类
    public String SumPrice(int quantity, float price){
        float sum = quantity * price;
        String StrSum = Float.toString(sum);
        return StrSum;
    }
}
