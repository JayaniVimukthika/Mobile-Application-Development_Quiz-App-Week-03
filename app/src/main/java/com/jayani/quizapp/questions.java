package com.jayani.quizapp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class questions extends AppCompatActivity {
    List<questionModel> QuestionsList;
    questionModel question_Model;
    int index = 0;

    TextView cardQuestion, optionA, optionB, optionC, optionD, countComplete;
    CardView cardA,cardB, cardC, cardD;
    Button Next;


    int correctCount = 0;
    int wrongCount = 0;
    int progressCount = 0;
    int countCompleteVal =0;

    TextView nameMsg;

    ProgressBar progress_bar_1;
    public static ArrayList<questionModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        list = new ArrayList<>();
        list.add(new questionModel("What is an activity in Android?",
                "A) Activity performs the actions on the screen",
                "B) Manage the Application content",
                "C) Screen UI",
                "D) None of the above",
                "A) Activity performs the actions on the screen"));
        list.add(new questionModel("What programming language is primarily used for Android app development?",
                "A) C++",
                "B) Python",
                "C) Java",
                "D) Swift",
                "C) Java"));
        list.add(new questionModel("What is an APK in Android?",
                "A) A type of permission",
                "B) A configuration file",
                "C) Android Package Kit",
                "D) A debugging tool",
                "C) Android Package Kit"));
        list.add(new questionModel("What is the name of Android's virtual machine?",
                "A) VirtualBox",
                "B) Dalvik",
                "C) JVM",
                "D) ART",
                "B) Dalvik"));
        list.add(new questionModel("Which file specifies the essential features of an Android app?",
                "A) build.gradle",
                "B) MainActivity.java",
                "C) AndroidManifest.xml",
                "D) settings.gradle",
                "D) settings.gradle"));


        cardQuestion = findViewById(R.id.cardQuestion);
        optionA = findViewById(R.id.cardOptionA);
        optionB = findViewById(R.id.cardOptionB);
        optionC = findViewById(R.id.cardOptionC);
        optionD = findViewById(R.id.cardOptionD);

        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD = findViewById(R.id.cardD);

        Next = findViewById(R.id.btnNext);
        progress_bar_1 = findViewById(R.id.progress_bar_1);
        nameMsg = findViewById(R.id.nameMsg);
        countComplete = findViewById(R.id.countComplete);

        QuestionsList = list;
        Collections.shuffle(QuestionsList);
        question_Model = QuestionsList.get(index);

        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));

        Next.setClickable(false);
        progress_bar_1.setProgress(progressCount);
        countComplete.setText(countCompleteVal + "/5");

        SharedPreferences sp = getSharedPreferences("user_name_store", Activity.MODE_PRIVATE);
        String name = sp.getString("user_name", "");

        nameMsg.setText("Hi  " + name );
        setAllData();
    }

    public void enableButton(){
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }

    public void disableButton(){
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }

    private void setAllData() {
        cardQuestion.setText(question_Model.getQuestion());
        optionA.setText(question_Model.getoptionA());
        optionB.setText(question_Model.getoptionB());
        optionC.setText(question_Model.getoptionC());
        optionD.setText(question_Model.getoptionD());

    }

    private void InitializeFields() {

    }

    public void Correct(CardView cardView){

        cardView.setCardBackgroundColor(getResources().getColor(R.color.green));

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameMsg.setVisibility(View.GONE);
                index++;
                question_Model = list.get(index);
                resetColour();
                enableButton();
                setAllData();
            }
        });

    }

    public void Wrong(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.red));

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameMsg.setVisibility(View.GONE);
                wrongCount++;
                if (index < list.size() -1){
                    index++;
                    question_Model = list.get(index);
                    resetColour();
                    enableButton();
                    setAllData();
                }
                else{
                    gameStatus();
                }
            }
        });
    }

    private void gameStatus() {
        Intent intent = new Intent(getApplicationContext(), GameStatus.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong", wrongCount);
        startActivity(intent);

    }

    public void resetColour(){

        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        progressCount = progressCount+20;
        countCompleteVal = countCompleteVal +1;
        countComplete.setText(countCompleteVal + "/5");
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (question_Model.getoptionA().equals(question_Model.getAns())){
            cardA.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;

            if (index < list.size() -1){
                Correct(cardA);
            }else{
                gameStatus();
            }
        }else{
            Wrong(cardA);
        }
    }

    public void OptionBClick(View view) {
        progressCount = progressCount+20;
        countCompleteVal = countCompleteVal +1;
        countComplete.setText(countCompleteVal + "/5");
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (question_Model.getoptionB().equals(question_Model.getAns())){
            cardB.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;

            if (index < list.size() -1){
                Correct(cardB);
            }else{
                gameStatus();
            }
        }else{
            Wrong(cardB);
        }
    }


    public void OptionCClick(View view) {
        progressCount = progressCount+20;
        countCompleteVal = countCompleteVal +1;
        countComplete.setText(countCompleteVal + "/5");
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (question_Model.getoptionC().equals(question_Model.getAns())){
            cardC.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;

            if (index < list.size() -1){
                Correct(cardC);
            }else{
                gameStatus();
            }
        }else{
            Wrong(cardC);
        }
    }

    public void OptionDClick(View view) {
        progressCount = progressCount+20;
        countCompleteVal= countCompleteVal+1;
        countComplete.setText(countCompleteVal + "/5");
        progress_bar_1.setProgress(progressCount);
        disableButton();
        Next.setClickable(true);
        if (question_Model.getoptionD().equals(question_Model.getAns())){
            cardD.setBackgroundColor(getResources().getColor(R.color.green));
            correctCount++;


            if (index < list.size() -1){
                Correct(cardD);
            }else{
                gameStatus();
            }
        }else{
            Wrong(cardD);
        }
    }
}


