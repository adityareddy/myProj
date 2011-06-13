package com.myProj;



import android.content.Intent;
import android.os.Bundle;

public class ActivityOne extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("ActivityOneChild", new Intent(this,ActivityOneChild.class));
    }
}
