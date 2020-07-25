package com.example.ic06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_exit,btn_start;
    ImageView imageView;
    ArrayList<Questions> questions1 = new ArrayList<>();
    ProgressBar progressBar;
    private static final int REQ_CODE=0x001;
    public static String USER_KEY="user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_exit=findViewById(R.id.exit_Button);
        btn_start=findViewById(R.id.start_trivia);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        imageView=findViewById(R.id.triviaImage);
        new GetJSONData().execute();
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
                intent.putExtra("BUNDLE",questions1);
                startActivity(intent);
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class GetJSONData extends AsyncTask<String,Void,ArrayList<Questions>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Questions> questions) {
            super.onPostExecute(questions);
            progressBar.setVisibility(View.INVISIBLE);
            btn_exit.setEnabled(true);
            btn_start.setEnabled(true);
            imageView.setImageDrawable(getDrawable(R.drawable.trivia_image));
            questions1=questions;
        }

        @Override
        protected ArrayList<Questions> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            try {
                String url = "http://dev.theappsdr.com/apis/trivia_json/index.php";
                URL url1 = new URL(url);
                connection = (HttpURLConnection) url1.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF8");
                    JSONObject root = new JSONObject(json);

                    JSONArray questions = root.getJSONArray("questions");

                    for (int i=0;i<questions.length();i++){
                        JSONObject questionsJSON=questions.getJSONObject(i);
                        Questions question=new Questions();
                        question.id=questionsJSON.getInt("id");
                        question.question=questionsJSON.getString("text");
                        if(questionsJSON.has("image"))
                            question.url=questionsJSON.getString("image");
                        else
                            question.url="No image found";
                        JSONObject choices=questionsJSON.getJSONObject("choices");
                        JSONArray choiceslist=choices.getJSONArray("choice");
                        String choicelist[]=new String[choiceslist.length()];
                            for(int k = 0; k < choiceslist.length(); k++) {
                                choicelist[k] = choiceslist.getString(k);
                            }
                            question.choices=choicelist;
                            question.answer=choices.getString("answer");

                            questions1.add(question);

//                        for(int j = 0; j < choiceslist.length(); j++){
//                            choicelist[i] =choiceslist.getString(i);
//                        }
//                        choiceObject.choice=choicelist;
//                        choiceObject.answer=choiceslist.getString("answer");
                    }

                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return questions1;
        }
    }
}


