package com.example.example_model_04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
                    System.out.println(code);
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
                        Toast.makeText(getApplicationContext(),
                                "Se registro el usuario",
                                Toast.LENGTH_LONG).show();
                       limpiarRegistro();
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
                        table.removeAllViews();
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

    public void limpiarRegistro(){
        codeStudent.setText("");
        course.setText("");
        teacher.setText("");
        grade.setText("");
    }




    public void showResults(int code,HashMap<String,Grades>gradesList,HashMap<Integer,Student>studentList){
       /*   StudentList
       *    {123698={123698,"Valentina","123646","valentina@gmail.com"},
       *     741852={741852,"Erick","310258639","erick@gmail.com"},
       *     ...
       *     456987={456987,"Diana","32569878","diana@gmail.com"}
       *    }
       *
       *    GradeList
       *    { "lu23sk23"={"lu23sk23",123698,"APPS","Luis","2023-11-01",5},
       *      "ka52los4"={"ka52los4",123698,"Base de datos","Luis","2023-10-29",4.5},
       *       ...
       *      "ilpow41"={"ilpow41",741852,"APPS","Luis","2023-11-01",4}
               }
       *
       * */

        // El usuario quiere consultar las notas del estudiantes con código: 123698

        //381
        TableRow row1= new TableRow(this);
        TextView nameTitle= new TextView(this);
        nameTitle.setText("Estudiante");
        nameTitle.setWidth(140);
        nameTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        nameTitle.setBackgroundColor(Color.CYAN);

        TextView courseTitle= new TextView(this);
        courseTitle.setText("Curso");
        courseTitle.setWidth(130);
        courseTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        courseTitle.setBackgroundColor(Color.CYAN);

        TextView gradeTitle= new TextView(this);
        gradeTitle.setText("Nota");
        gradeTitle.setWidth(80);
        gradeTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        gradeTitle.setBackgroundColor(Color.CYAN);

        TextView dateTitle= new TextView(this);
        dateTitle.setText("Fecha");
        dateTitle.setWidth(80);
        dateTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        dateTitle.setBackgroundColor(Color.CYAN);

        row1.addView(nameTitle);
        row1.addView(courseTitle);
        row1.addView(gradeTitle);
        row1.addView(dateTitle);

        table.addView(row1);

        for (String i:gradesList.keySet()){
            if(gradesList.get(i).getStudent_code()==code){
            //    newList.put(i,gradesList.get(i));
                TableRow row= new TableRow(this);

                TextView name= new TextView(this);
                Student object= studentList.get(code);
                name.setText(object.getName());
                name.setWidth(140);
                // name.setText(studentList.get(code).get(name));

                TextView courseName= new TextView(this);
                courseName.setText(gradesList.get(i).getCourse());
                courseName.setWidth(140);

                TextView finalGrade= new TextView(this);
                finalGrade.setText(gradesList.get(i).getGrade()+"");
                finalGrade.setWidth(80);

                TextView date= new TextView(this);
                date.setText(gradesList.get(i).getDate());
                date.setWidth(80);

                row.addView(name);
                row.addView(courseName);
                row.addView(finalGrade);
                row.addView(date);

                table.addView(row);
            }
        }





    }







}