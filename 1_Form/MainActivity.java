package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; import
        android.os.Bundle; import
        android.view.View;
import android.widget.ArrayAdapter; import
        android.widget.Button; import
        android.widget.EditText; import
        android.widget.RadioGroup; import
        android.widget.Spinner;
public class MainActivity extends AppCompatActivity { @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
    final EditText name=findViewById(R.id.name1);
    final EditText address=findViewById(R.id.addrress1);
    final EditText dob=findViewById(R.id.date1);
    final RadioGroup gender=findViewById(R.id.radioGroup); final
    Spinner spinner=findViewById(R.id.spinner1); Button
            submit=findViewById(R.id.button2);
    Button reset=findViewById(R.id.button);
    ArrayAdapter<CharSequence>
            adapter=ArrayAdapter.createFromResource(this,R.array.maritalstatus,
            android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
//reset
    reset.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view){
            startActivity(new
                    Intent(getApplicationContext(),MainActivity.class));
        }
    });
    submit.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view){
            startActivity(new
                    Intent(getApplicationContext(),MainActivity.class));
        }
    });
}
}