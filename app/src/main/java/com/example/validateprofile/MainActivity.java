package com.example.validateprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.validatelibrary.PasswordTypes;
import com.example.validatelibrary.ValidateClass;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private EditText main_EDT_email,main_EDT_phone,main_EDT_name,main_EDT_age,main_EDT_password,main_EDT_date;
    private MaterialButton main_BTN_checkMail,main_BTN_checkPhone,main_BTN_checkName,
            main_BTN_checkAge,main_BTN_checkPassword,main_BTN_checkDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        createListeners();
    }

    private void createListeners() {
        main_BTN_checkMail.setOnClickListener(v-> checkIfEmail());
        main_BTN_checkPhone.setOnClickListener(v-> checkIfPhone());
        main_BTN_checkName.setOnClickListener(v-> checkIfName());
        main_BTN_checkAge.setOnClickListener(v-> checkIfAge());
        main_BTN_checkPassword.setOnClickListener(v-> checkIfPassword());
        main_BTN_checkDate.setOnClickListener(v-> checkIfDate());
    }



    private void checkIfEmail() {
        String mail = main_EDT_email.getText().toString();
        if(ValidateClass.checkEmail(mail)){
            Toast.makeText(MainActivity.this, "Valid Email", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void checkIfPhone() {
        String phone = main_EDT_phone.getText().toString();
        if(ValidateClass.checkPhoneNumber(phone)){
            Toast.makeText(MainActivity.this, "Valid Phone", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void checkIfName() {
        String name = main_EDT_name.getText().toString();
        if(ValidateClass.checkValidName(name)){
            Toast.makeText(MainActivity.this, "Valid Name", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void checkIfAge() {
        String age = main_EDT_age.getText().toString();
        if(ValidateClass.checkAge(age)){
            int ageLegal = Integer.parseInt(age);
            if(ValidateClass.checkLegalAge(ageLegal, 18)){
                Toast.makeText(MainActivity.this, "Valid and Legal Age", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this, "Valid and Illegal Age", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void checkIfPassword() {
        String password = main_EDT_password.getText().toString();
        PasswordTypes type = ValidateClass.checkPasswordType(password);
        Toast.makeText(MainActivity.this, "type = "+type, Toast.LENGTH_LONG).show();
    }

    private void checkIfDate() {
        String date = main_EDT_date.getText().toString();
        if(ValidateClass.checkDateFormat(date)){
            Toast.makeText(MainActivity.this, "Valid Date", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
        }

        if(ValidateClass.checkBirthDate(date,18)){
            Toast.makeText(MainActivity.this, "Legal Date", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Illegal Date", Toast.LENGTH_LONG).show();
        }
    }






    private void findViews() {
        main_EDT_email = findViewById(R.id.main_EDT_email);
        main_EDT_phone = findViewById(R.id.main_EDT_phone);
        main_EDT_name = findViewById(R.id.main_EDT_name);
        main_EDT_age = findViewById(R.id.main_EDT_age);
        main_EDT_password = findViewById(R.id.main_EDT_password);
        main_EDT_date = findViewById(R.id.main_EDT_date);



        main_BTN_checkMail = findViewById(R.id.main_BTN_checkMail);
        main_BTN_checkPhone = findViewById(R.id.main_BTN_checkPhone);
        main_BTN_checkName = findViewById(R.id.main_BTN_checkName);
        main_BTN_checkAge = findViewById(R.id.main_BTN_checkAge);
        main_BTN_checkPassword = findViewById(R.id.main_BTN_checkPassword);
        main_BTN_checkDate = findViewById(R.id.main_BTN_checkDate);
    }
}