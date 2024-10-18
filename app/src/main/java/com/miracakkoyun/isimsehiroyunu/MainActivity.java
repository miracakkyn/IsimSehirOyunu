package com.miracakkoyun.isimsehiroyunu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void normalGameButton(View view){
        //Normal Oyuna geçme aktivite kodu
        Intent normalGame=new Intent(this,NormalOyunActivity.class);
        finish();
        startActivity(normalGame);
    }
    public void timedGameButton(View view){
        //Normal Oyuna geçme aktivite kodu
        Intent timerGame=new Intent(this, SureliOyunActivity.class);
        finish();
        startActivity(timerGame);
    }
    public void exitButton(View view){
        //Normal Oyuna geçme aktivite kodu
        finish();

    }

}