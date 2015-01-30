package com.epam.training.gamingassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class StartActivity extends ActionBarActivity {

    public static final int REQUEST_LOGIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button = (Button) findViewById(R.id.authorization);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), REQUEST_LOGIN);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.TOKEN, data.getStringExtra(MainActivity.TOKEN));
            intent.putExtra(MainActivity.USER_ID, data.getStringExtra(MainActivity.USER_ID));
            startActivity(intent);
            finish();
        } else {
            if (requestCode == REQUEST_LOGIN && resultCode == RESULT_CANCELED) {
                Toast.makeText(this, data.getStringExtra(MainActivity.ERROR), Toast.LENGTH_SHORT).show();
            }
        }
    }


}
