package com.jayani.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameStatus extends AppCompatActivity {
    TextView result, congtsMsg;
    Button btnNewQuiz, btnFinish;

    int correct, wrong;
    String namekey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_status);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        result = findViewById(R.id.result);
        congtsMsg = findViewById(R.id.congratsMsg);
        btnFinish = findViewById(R.id.btnFinish);
        btnNewQuiz = findViewById(R.id.btnNew);

        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);

        result.setText(correct + " / 5");

        SharedPreferences sp = getSharedPreferences("user_name_store", Activity.MODE_PRIVATE);
        String name = sp.getString("user_name", "");

        congtsMsg.setText("Congratulations  " + name + "..");

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("user_name_store", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                finishAffinity();
                System.exit(0);
            }
        });

        btnNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });



    }
}