package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class EnterItems extends AppCompatActivity {
int nextItemID = 0;
LinearLayout rl;
Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_items);
        rl = findViewById(R.id.EnterItems);
        //function to create row
        createRow(rl);
        //bring in buttons from layout
        Button addRowBtn = findViewById(R.id.btnAddRow);
        saveBtn = findViewById(R.id.btnSaveIngredients);
        //on click listener for AddRowbutton
       addRowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRow(rl);
            }
        });
        //listener fo save button
       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //create an array list of strings and populate items into it.
               ArrayList<String> items = new ArrayList<>();
               for(int i=0; i<nextItemID; i++){
                   EditText current = findViewById(i);
                   items.add(current.getText().toString());
               }

               //package intent and start activity
               Intent intent = new Intent(getApplicationContext(), GetQuantities.class);
               intent.putExtra("Rows", nextItemID);
               intent.putStringArrayListExtra("Items", items);
               startActivity(intent);
           }
       });

    }

    protected void createRow(LinearLayout rl){
        //increment rows, create EditText and add to layout

        EditText et  = new EditText(this);
        et.setId(nextItemID);
        et.setHeight(150);
        et.setWidth(350);
        et.setTextSize(25);
        et.setText("");
        et.setHint("Enter next Item");

        rl.addView(et);
        nextItemID ++;



    }
}
