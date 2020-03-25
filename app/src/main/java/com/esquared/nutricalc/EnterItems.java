package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EnterItems extends AppCompatActivity {
int nextItemID = 1;
LinearLayout rl;
Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_items);
        rl = findViewById(R.id.EnterItems);

        createRow(rl);

        Button addRowBtn = findViewById(R.id.btnAddRow);
        saveBtn = findViewById(R.id.btnSaveIngredients);

       addRowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRow(rl);
            }
        });

       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), GetQuantities.class);
               intent.putExtra("Rows", nextItemID);
               startActivity(intent);
           }
       });

    }

    protected void createRow(LinearLayout rl){

        EditText et = new EditText(this);
        et.setId(nextItemID);
        et.setHeight(150);
        et.setWidth(350);
        et.setTextSize(25);
        et.setText("Test " + nextItemID);

        rl.addView(et);
        nextItemID ++;


    }
}
