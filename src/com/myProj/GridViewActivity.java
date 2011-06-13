package com.myProj;

import android.content.Intent;
import android.os.Bundle;

public class GridViewActivity extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("GridViewChild", new Intent(this,GridViewChild.class));
    }
}
