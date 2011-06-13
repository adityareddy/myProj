package com.myProj;



import android.content.Intent;
import android.os.Bundle;

public class ActivityTwo extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("OptionsActivity", new Intent(this,jsonparsemain.class));
    }
    
}
