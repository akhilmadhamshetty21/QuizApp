package com.example.ic06;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements OptionsAdapter.InteractWithTriviaActivity {
    Button exit_btn,next_btn;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView question_id;
    TextView qtn,time_left;
    public static ArrayList<Questions> questions=new ArrayList<>();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Integer> scores=new ArrayList<>();
    ProgressBar progressBar;
    int index=0;
    int score=0;
    ScoreActivity sco=new ScoreActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivia_layout);
        exit_btn=findViewById(R.id.back_button);
        next_btn=findViewById(R.id.nxt_button);
        imageView=findViewById(R.id.imageView1);
        question_id=findViewById(R.id.question_id);
        qtn=findViewById(R.id.qtn_btn);
        time_left=findViewById(R.id.time_left);
        progressBar=findViewById(R.id.progressBarTrivia);
        recyclerView=findViewById(R.id.recyclerView);
        if(getIntent()!=null && getIntent().getExtras()!=null){
            questions=(ArrayList<Questions>)getIntent().getExtras().getSerializable("BUNDLE");
        }
        Log.d("demo",questions.toString());
         new GetImageAsync(imageView).execute(questions.get(0).getUrl());
         question_id.setText(questions.get(0).getQuestion());
         qtn.setText("Q"+1);
        String string=questions.get(index).getUrl();
        Log.d("urlimp",string);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new OptionsAdapter(questions.get(0),this);
        recyclerView.setAdapter(adapter);
        new CountDownTimer(2*60*1000, 1000) {
            @Override
            public void onTick(long l) {
                long minute = l/1000/60;
                long second = (l - minute*60*1000)/1000;
                time_left.setText(minute+" : "+second);
            }

            @Override
            public void onFinish() {
                Intent i1=new Intent(TriviaActivity.this,StatsActivity.class);
                Log.d("score1",+score+"");
                i1.putExtra("Score",score);
                startActivity(i1);
            }
        }.start();

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               index=index+1;
//                new GetImageAsync(imageView).execute(questions.get(index).getUrl());
//                adapter=new OptionsAdapter(questions.get(index));
//                recyclerView.setAdapter(adapter);
//                qtn.setText("Q"+index);
//                question_id.setText(questions.get(index).getQuestion());
                if(index==questions.size()-1) {
                    Intent i1=new Intent(TriviaActivity.this,StatsActivity.class);
                    i1.putExtra("Score",score);
                    startActivity(i1);
                }
                else
                    loadNextQuestion(index);
            }
        });
    }

    public void searchForItem(int position, Questions questions1){

        String selectedOption=questions1.getChoices()[position];
        String answer=questions1.getChoices()[Integer.parseInt(questions1.answer)-1];

        Log.d("answer",selectedOption + "answer" +answer);
        if(selectedOption.equals(answer)) {
            score = 1;
            scores.add(score);
        }
        if(index==questions.size()-1)
            Log.d("score", String.valueOf(score));
    }

    public void loadNextQuestion(int index1) {
        Log.d("demo1",index1 +"");
        new GetImageAsync(imageView).execute(questions.get(index1).getUrl());
        adapter=new OptionsAdapter(questions.get(index1),this);
        recyclerView.setAdapter(adapter);
        qtn.setText("Q"+(index1+1)+"");
        question_id.setText(questions.get(index1).getQuestion());
    }

    @Override
    public void searchItem(int position, Questions question) {
        String selectedOption=question.getChoices()[position];
        String answer=question.getChoices()[Integer.parseInt(question.answer)-1];

        Log.d("answer",selectedOption + "answer" +answer);
        if(selectedOption.equals(answer)) {
            score = score+1;
        }
    }

    private class GetImageAsync extends AsyncTask<String, Void, Void> {
        ImageView imageView1;
        Bitmap bitmap = null;

        public GetImageAsync(ImageView iv) {

            imageView1 = iv;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... params) {

            HttpURLConnection connection = null;
            bitmap = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                }
                else
                    bitmap=null;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //Close open connection
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (bitmap != null && imageView1 != null) {
                Log.d("URL","I am here");
                imageView1.setImageBitmap(bitmap);
                progressBar.setVisibility(View.INVISIBLE);
            }
            else {
                imageView.setImageResource(R.mipmap.ic_launcher);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(TriviaActivity.this,"Can't Load URL",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
