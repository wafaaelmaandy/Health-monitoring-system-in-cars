package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNumbers extends Activity {
    EditText number1,number2 ,number3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnumbers);
        number1 =(EditText)findViewById(R.id.number1);
        number2 =(EditText)findViewById(R.id.number2);
        number3 =(EditText)findViewById(R.id.number3);

    }

    public void save(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();

        String n1 = number1.getText().toString();
        String n2 = number2.getText().toString();
        String n3 =number3.getText().toString();

        editor.putString("number1",n1);
        editor.putString("number2",n2);
        editor.putString("number3",n3);
        editor.apply();
        Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
    }

    public void display(View view) {
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
        }


    public void displayAll(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        String n1 = sharedPreferences.getString("number1","not available");
        String n2 = sharedPreferences.getString("number2","not available");
        String n3 = sharedPreferences.getString("number3","not available");
        number1.setText(n1);
        number2.setText(n2);
        number3.setText(n3);
    }

    public void deleteAll(View view) {


        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();


        editor.commit();
        number2.setText("");
        number1.setText("");
        number3.setText("");
    }

    public void updeteN1(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();

        String n1 = number1.getText().toString();
        editor.putString("number1",n1);

        editor.apply();
        Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
    }

    public void updeteN2(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();

        String n2 = number2.getText().toString();
        editor.putString("number2",n2);

        editor.apply();
        Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
    }

    public void updeteN3(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();

        String n3 = number3.getText().toString();
        editor.putString("number3",n3);

        editor.apply();
        Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();

    }
}
