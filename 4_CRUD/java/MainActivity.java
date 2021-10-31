package com.example.crud;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        db=new DBHelper(this);
        Button insert=findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_insert);
                EditText name= findViewById(R.id.name);
                RadioGroup gender= findViewById(R.id.gender);
                Spinner department= findViewById(R.id.department);
                EditText salary= findViewById(R.id.salary);
                EditText employee_code= findViewById(R.id.employee_code);
                Button insert=findViewById(R.id.insert);
                insert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(name.getText())) {
                            Toast.makeText(MainActivity.this, "Enter your name ",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(salary.getText())) {
                            Toast.makeText(MainActivity.this, "Enter your salary ",
                                    Toast.LENGTH_SHORT).show();
                        } else if (!(((RadioButton) findViewById(R.id.male)).isChecked() ||
                                ((RadioButton) findViewById(R.id.female)).isChecked())) {
                            Toast.makeText(MainActivity.this, "Select Your Gender ",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(employee_code.getText())) {
                            Toast.makeText(MainActivity.this, "Enter your employee code",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String name_value = name.getText().toString();
                            String department_value =
                                    department.getSelectedItem().toString();
                            String employee_code_value =
                                    employee_code.getText().toString();
                            String gender_value = ((RadioButton)
                                    findViewById(gender.getCheckedRadioButtonId())).getText().toString();
                            Double salary_value =
                                    Double.parseDouble(String.valueOf(salary.getText()));
                            Boolean checkInsertData =
                                    db.insertUserdata(employee_code_value, name_value, gender_value,
                                            department_value, salary_value);
                            if (checkInsertData == true)
                            {
                                Toast.makeText(MainActivity.this, "New Entry Inserted",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this,MainActivity.class);
                                finish();
                                startActivity(i);
                            }
                            else
                                Toast.makeText(MainActivity.this, "New Entry Not Inserted",
                                        Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Button back=findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
            }
        });
        Button update=findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_update);
                EditText name = findViewById(R.id.name);
                EditText salary = findViewById(R.id.salary);
                EditText employee_code = findViewById(R.id.employee_code);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
                Button get = findViewById(R.id.get);
                get.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(employee_code.getText())) {
                            Toast.makeText(MainActivity.this, "Enter your employee code", Toast.LENGTH_SHORT).show();
                        } else {
                            String employee_code_value =
                                    employee_code.getText().toString();
                            Cursor res = db.getUserdata(employee_code_value);
                            if (res.getCount() == 0) {
                                Toast.makeText(MainActivity.this, "No Entry Exists",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                res.moveToNext();
                                String name_value = res.getString(1);
                                Double salary_value = res.getDouble(4);
                                name.setText(name_value);
                                salary.setText(salary_value.toString());
                            }
                        }
                    }
                });
                Button update = findViewById(R.id.update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name_value = name.getText().toString();
                        String employee_code_value =
                                employee_code.getText().toString();
                        Double salary_value =
                                Double.parseDouble(String.valueOf(salary.getText()));
                        Boolean checkUpdateData =
                                db.updateUserdata(employee_code_value, name_value, salary_value);
                        if (checkUpdateData == true)
                            Toast.makeText(MainActivity.this, "Entry Updated",
                                    Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "New Entry Not Updated",
                                    Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_delete);
                Button back=findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
                Button delete=findViewById(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText employee_code = findViewById(R.id.employee_code);
                        String employee_code_value =
                                employee_code.getText().toString();
                        Boolean checkDeleteData =
                                db.deleteUserdata(employee_code_value);
                        if (checkDeleteData == true)
                            Toast.makeText(MainActivity.this, "Entry Deleted",
                                    Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Entry Not Deleted",
                                    Toast.LENGTH_SHORT).show();
                        employee_code.setText("");
                    }
                });
            }
        });
        Button delete_all = findViewById(R.id.delete_all);
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkDeleteData = db.deleteAll();
                if (checkDeleteData == true)
                    Toast.makeText(MainActivity.this, "Table Deleted",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Table Not Deleted",
                            Toast.LENGTH_SHORT).show();
            }
        });
        Button retrieve = findViewById(R.id.retrieve_id);
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_retrieve);
                EditText name = findViewById(R.id.name);
                EditText department= findViewById(R.id.department);
                EditText gender = findViewById(R.id.gender);
                EditText salary = findViewById(R.id.salary);
                EditText employee_code = findViewById(R.id.employee_code);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
                Button view = findViewById(R.id.view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(employee_code.getText())) {
                            Toast.makeText(MainActivity.this, "Enter your employee code ", Toast.LENGTH_SHORT).show();
                        } else {
                            String employee_code_value =
                                    employee_code.getText().toString();
                            Cursor res = db.getUserdata(employee_code_value);
                            if (res.getCount() == 0) {
                                Toast.makeText(MainActivity.this, "No Entry Exists",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                res.moveToNext();
                                String name_value = res.getString(1);
                                String gender_value= res.getString(2);
                                String department_value = res.getString(3);
                                Double salary_value = res.getDouble(4);
                                name.setText(name_value);
                                gender.setText(gender_value.toString());
                                department.setText(department_value.toString());
                                salary.setText(salary_value.toString());
                            }
                        }
                    }
                });
            }
        });
        Button retrieve_dept=findViewById(R.id.retrieve_dept);
        retrieve_dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_retrieve_department);
                Spinner department= findViewById(R.id.department);
                EditText txt = findViewById(R.id.txt);
                Button back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
                Button view = findViewById(R.id.view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String department_value =
                                department.getSelectedItem().toString();
                        Cursor res = db.getUserdataDept(department_value);
                        if (res.getCount() == 0) {
                            txt.setText("");
                            Toast.makeText(MainActivity.this, "No Entry Exists",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String temp="";
                            txt.setText("");
                            while(res.moveToNext()) {
                                String name_value = res.getString(1);
                                String gender_value = res.getString(2);
                                String employee_code_value = res.getString(0);
                                Double salary_value = res.getDouble(4);
                                temp+="Name : "+name_value+"\nEmployee Code : "+employee_code_value+"\nGender : "+gender_value+"\nSalary : "+salary_value+"\n\n";
                                txt.setText(temp);
                            }
                        }
                    }
                });
            }
        });
    }
}