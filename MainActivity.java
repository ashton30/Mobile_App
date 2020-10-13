package com.example.list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemControl;
    private ListView list;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemControl = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(itemControl);

        setupListViewListener();
    }

    public void onAddItem(View view){
        EditText newItem = (EditText) findViewById(R.id.newItem);
        String itemText = newItem.getText().toString();
        itemControl.add(itemText);
        newItem.setText("");
    }

    private void setupListViewListener() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > adapter, View item, int pos, long id){
                TextView text = (TextView) item;
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                items.remove(pos);
                items.add(pos, text.getText().toString());
                itemControl.notifyDataSetChanged();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick (AdapterView < ? > adapter, View item, int pos, long id) {
                items.remove(pos);
                itemControl.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void editTitle(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Edit Title");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                title = findViewById(R.id.title);
                title.setText(input.getText().toString());
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }

    public void goBack(View view){
        Intent intent = new Intent(this, StartScreen.class);
        startActivity(intent);
    }

}