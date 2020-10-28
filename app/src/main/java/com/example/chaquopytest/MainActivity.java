package com.example.chaquopytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        btn = (Button)findViewById(R.id.btn);
        tv = (TextView)findViewById(R.id.tv);

        initPython();

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("script.py");  // Enter name of python file to run

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                  // funct name   // first arg            // second arg
                PyObject obj = pyobj.callAttr("main", et1.getText().toString(),et2.getText());

                // obj will contain result, so set its result to textview

                tv.setText(obj.toString());


            }
        });
    }

    void initPython() { // Initialize python in Java
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

}