package com.dmdddm.ads;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentCenter extends Fragment implements View.OnClickListener{
    /**声明控件**/
    private TextView uName;

    public FragmentCenter() {
        // Required empty public constructor
    }
    /**页面返回时的调用的方法**/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
            uName.setText(data.getStringExtra("UserName"));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        /**按钮点击页面跳转在这里写***/
        final Intent iLogin = new Intent(getActivity(),login.class);
        uName = view.findViewById(R.id.uName);
        uName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(iLogin ,1);
            }
        });
        //初始化按钮
        Button mOrder = view.findViewById(R.id.order);
        Button mLocation = view.findViewById(R.id.Location);
        View vCollect = view.findViewById(R.id.Collect);


        mOrder.setOnClickListener(this) ;
        mLocation.setOnClickListener(this);
        vCollect.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Location:
                Intent intent = new Intent(getActivity(),Location.class);
                startActivity(intent);break;
            case R.id.Collect:
                Intent intent2 = new Intent(getActivity(),Collect.class);
                startActivity(intent2);break;
            case R.id.order:
                Intent intent3 = new Intent(getActivity(),MyOrder.class);
                startActivity(intent3);break;
            default:break;

        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
