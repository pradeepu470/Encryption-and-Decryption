package com.pradeep.encriptionAndDiscription;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EncriptDecriptActivity extends AppCompatActivity {
    TextView mDisplay;
    EditText mString;
    Button mSubmit;
    String mReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encript_decript);
        mDisplay = (TextView) findViewById(R.id.display);
        mString = (EditText) findViewById(R.id.editText);
        mSubmit = (Button) findViewById(R.id.submit);
        mReason = (this.getIntent()).getStringExtra("reason");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView current = (TextView) toolbar.findViewById(R.id.current_toolbar);
        current.setText(mReason);
        TextView back = (TextView) toolbar.findViewById(R.id.privious_toolbar);
        back.setText("<crypto String");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mString.getText().toString();
                dismissKeyboard();
                try {
                    if (mReason.equals("encrypt")) {
                        mDisplay.setText(encryption(data));
                    } else {
                        mDisplay.setText(decryption(data));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        mString.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mSubmit.setEnabled(false);
                } else {
                    mSubmit.setEnabled(true);
                }
            }
        });
    }

    public String encryption(String value) {
        String output = "";
        char data[] = value.toCharArray();
        int count;
        for (int i = 0; i < data.length; i += count) {
            count = 1;
            int add = 0;
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] == data[j]) {
                    count++;
                    continue;
                } else {
                    output = output + data[i] + count;
                    add = -1;
                    break;
                }
            }
            if (add == 0) {
                output = output + data[i] + count;
            }
        }
        return output;

    }

    public void dismissKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public String decryption(String value) {
        String output = "";
        char data[] = value.toCharArray();
        if( (!Character.isAlphabetic(data[0]))|| (!Character.isDigit(data[data.length-1]))) {
            return "invalid code.";
        }
        for (int i = 0; i < data.length; i++) {
            String temp = "";
            for(int j = i; j< data.length;j++) {
                Boolean flag = Character.isDigit(data[j]);
                if(flag) {
                    temp = temp + data[j];
                } else {
                    break;
                }
            }
            if(temp.length() > 0) {
                Log.e("tag",""+"temp:"+temp);
                int times = Integer.parseInt(temp);
                Log.e("tag",""+times);
                for (int j = 0; j < times ; j++) {
                    if(i != 0) {
                        output = output + " " + data[i - 1];
                    } else {
                        output = output +data[i];
                    }
                }
            }
            i+=temp.length();
        }
        return output;
    }
}
