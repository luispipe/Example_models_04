package com.example.example_model_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    EditText nameStudent,email,phone,codeStudent,course,
    teacher,grade,date,searchStudent;

    Button registerUser, registerGrade, search;

    TableLayout table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameStudent=findViewById(R.id.editTextName);
        email= findViewById(R.id.editTextEmail);
        phone= findViewById(R.id.editTextPhone);

        codeStudent= findViewById(R.id.editTextStudentCode);
        course= findViewById(R.id.editTextCourse);
        teacher= findViewById(R.id.editTextTeacher);
        grade= findViewById(R.id.editTextGrade);
        date= findViewById(R.id.editTextDate);

        searchStudent= findViewById(R.id.editTextStudentCodeSearch);

        registerUser=findViewById(R.id.button);
        registerGrade= findViewById(R.id.button2);
        search= findViewById(R.id.button3);

        table=findViewById(R.id.tableStudent);



    }

    public void studentCodeGenerator(){

    }

    public boolean validateUser(){
        return true;
    }

    public void createUser(){

    }

    public void showResults(){

    }







}