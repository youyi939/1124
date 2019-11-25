package com.example.a1124;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.zip.Inflater;
public class MyService extends MainActivity{
    private MainActivity context;
    //传入Activity对象

    public MyService(MainActivity context) {
        this.context = context;
    }


    @JavascriptInterface
    public void jump(String txt){
        Log.d("aaaa",txt);



        Bundle bundle = new Bundle();
        bundle.putString("MainActivity", txt);
//首先有一个Fragment对象 调用这个对象的setArguments(bundle)传递数据
        msgFragment.setArguments(bundle);

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,msgFragment);
        transaction.commit();


    }
}
