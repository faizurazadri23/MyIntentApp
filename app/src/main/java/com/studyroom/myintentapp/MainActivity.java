package com.studyroom.myintentapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        Button btnMovewithData = findViewById(R.id.btn_move_with_data);
        Button btnMovewithObject = findViewById(R.id.btn_move_activity_object);
        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        Button btnMoveForResult= findViewById(R.id.btn_move_for_result);

        tvResult = findViewById(R.id.tv_result);

        btnMoveActivity.setOnClickListener(this);
        btnMovewithData.setOnClickListener(this);
        btnMovewithObject.setOnClickListener(this);
        btnDialPhone.setOnClickListener(this);
        btnMoveForResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;

            case R.id.btn_move_with_data:
                Intent  moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Dicoding Academy Boy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveWithDataIntent);
                break;

            case R.id.btn_move_activity_object:
                Person person = new Person();
                person.setName("FAIZURA ZADRI");
                person.setAge(5);
                person.setEmail("faizurazadri08@gmail.com");
                person.setCity("Solok");

                Intent moveactivityobject = new Intent(MainActivity.this, MoveWithObjectActivity.class);
                moveactivityobject.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
                startActivity(moveactivityobject);
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "085272667386";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }
    }
}