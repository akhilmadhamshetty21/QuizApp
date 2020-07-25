package com.example.ic06;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    Button quitButton,tryButton;
    ProgressBar progressBar;
    TextView result;
    int score=0,finalscore=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);
        quitButton=findViewById(R.id.quit_btn);
        tryButton=findViewById(R.id.try_again);
        progressBar=findViewById(R.id.progressBar3);
        result=findViewById(R.id.textViewResult);
        if(getIntent()!=null && getIntent().getExtras()!=null){
            score=(Integer)getIntent().getExtras().getInt("Score");
            finalscore=(score*100/16);
            progressBar.setProgress(finalscore);
            result.setText(finalscore+"%");
        }
        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(StatsActivity.this,MainActivity.class);
                startActivity(i1);
            }
        });
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
