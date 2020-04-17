package com.pradeep.encriptionAndDiscription;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mEncrypt,mDecrypt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEncrypt = (Button) findViewById(R.id.encrypt);
        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EncriptDecriptActivity.class);
                intent.putExtra("reason", "encrypt");
                startActivity(intent);
            }

        });
        mDecrypt = (Button) findViewById(R.id.decrypt);
        mDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EncriptDecriptActivity.class);
                intent.putExtra("reason", "decrypt");
                startActivity(intent);
            }
        });

    }
}
