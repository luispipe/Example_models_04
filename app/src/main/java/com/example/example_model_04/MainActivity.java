package com.example.example_model_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_model_04.Models.Grades;
import com.example.example_model_04.Models.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText nameStudent,email,phone,codeStudent,course,
    teacher,grade,searchStudent;

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
        searchStudent= findViewById(R.id.editTextStudentCodeSearch);

        registerUser=findViewById(R.id.button);
        registerGrade= findViewById(R.id.button2);
        search= findViewById(R.id.button3);

        table=findViewById(R.id.tableStudent);

        //Donde almacenaremos los estudiantes y sus notas
        HashMap<Integer, Student> studentsList= new HashMap<>();
        HashMap<String, Grades> gradesList= new HashMap<>();

        ArrayList<Integer> codesList= new ArrayList<>();

       /* Reciclar plastico1= new Reciclar("plastico",50.5,20000);
        ArrayList<Reciclar> listReciclar= new ArrayList<>();
        listReciclar.add(plastico1);*/

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameStudent.getText().toString();
                String emailStudent=email.getText().toString();
                String phoneStudent= phone.getText().toString();
                int code= studentCodeGenerator(codesList);
                codesList.add(code);

                if (name.isEmpty()||emailStudent.isEmpty()||phoneStudent.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Todos los campos deben" +
                            "estar diligenciados",Toast.LENGTH_LONG).show();
                } else{
                    Student studentObject= new Student(code,name,emailStudent,phoneStudent);
                    studentsList.put(code,studentObject);
                    limpiar();
                }

            }
        });

        registerGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code= codeStudent.getText().toString();
                String courseName= course.getText().toString();
                String teacherName= teacher.getText().toString();
                String gradeObtain= grade.getText().toString();

                if (code.isEmpty()||courseName.isEmpty()||teacherName.isEmpty()
                || gradeObtain.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Todos los campos" +
                            "deben estar diligenciados",Toast.LENGTH_LONG).show();
                }else{
                    int codeInt= Integer.parseInt(code);
                    if(validateUser(codeInt,codesList)){
                        //fecha Año - Mes - Día
                        LocalDate date= LocalDate.now();
                        //Tiempo hora-min-seg
                        //LocalTime tiempo= LocalTime.now();
                        //Fecha y hora
                        //LocalDateTime dateTime= LocalDateTime.now();
                       String dateGrade= date.toString();
                       String codeGrade= codeInt+courseName+dateGrade;
                       double finalGrade= Double.parseDouble(gradeObtain);
                       Grades gradeObject= new Grades(codeGrade,codeInt,courseName,teacherName,dateGrade,finalGrade);
                       gradesList.put(codeGrade,gradeObject);
                    }else{
                        Toast.makeText(getApplicationContext(),
                        "El estudiante no se encuentra en la base de datos",
                        Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code= searchStudent.getText().toString();
                if (code.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                "Debe ingresar el código del estudiante",Toast.LENGTH_LONG).show();
                }else{
                    int codeInt= Integer.parseInt(code);
                    if (validateUser(codeInt,codesList)){
                        showResults(codeInt,gradesList,studentsList);
                    }else{
                        Toast.makeText(getApplicationContext(),
                    "No se encuentra estudiante",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    public int studentCodeGenerator(ArrayList<Integer>list){
        int max=999999;
        int min=1;
        int random= (int)((Math.random()+min)*(max-min+1));

        while (validateUser(random,list)){
            random= (int)((Math.random()+min)*(max-min+1));
        }
        return random;
    }

    public boolean validateUser(int code, ArrayList<Integer>list){
        boolean exist=false;
        for (int i:list){
            if (i==code){
                exist=true;
                break;
            }
        }
        return exist;
    }

    public void limpiar(){
        nameStudent.setText("");
        phone.setText("");
        email.setText("");
    }


    public void showResults(int code,HashMap<String,Grades>gradesList,HashMap<Integer,Student>studentList){
   //     HashMap<String,Grades> newList= new HashMap<>();

        for (String i:gradesList.keySet()){
            if(gradesList.get(i).getStudent_code()==code){
            //    newList.put(i,gradesList.get(i));
                TableRow row= new TableRow(this);
                TextView name= new TextView(this);
                name.setText(studentList.get(code).getName());

                TextView courseName= new TextView(this);
                courseName.setText(gradesList.get(i).getCourse());

                TextView finalGrade= new TextView(this);
                finalGrade.setText(gradesList.get(i).getGrade()+"");

                TextView date= new TextView(this);
                date.setText(gradesList.get(i).getDate());

                row.addView(name);
                row.addView(courseName);
                row.addView(finalGrade);
                row.addView(date);

                table.addView(row);
            }
        }





    }







}