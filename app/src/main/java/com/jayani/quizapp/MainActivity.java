package com.jayani.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
//    public static ArrayList<ModelClass> list;
    Button start;
    EditText editTextName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.btnStart);
        editTextName = findViewById(R.id.editTextName);
        SharedPreferences sp = getSharedPreferences("user_name_store", Activity.MODE_PRIVATE);
        String name1 = sp.getString("user_name", "");

        editTextName.setText(name1);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your Name",Toast.LENGTH_SHORT).show();
                }else{
                    name = editTextName.getText().toString();
                    SharedPreferences user_name_store = getSharedPreferences("user_name_store", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = user_name_store.edit();
                    editor.putString("user_name", name);
                    editor.commit();
                    Intent intent = (new Intent(getApplicationContext(), questions.class));
                    startActivity(intent);
                }

            }
        });
    }
}