package com.example.exersice_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String EXTRA_MASSAGE = "MASSAGE";
    private ListView bj;
    DBHelper mydb;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        mydb = new DBHelper(this);
        ArrayList array_list =mydb.getAllCotacts();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, array_list);
        bj =(ListView)findViewById(R.id.listView1);
        bj.setAdapter(arrayAdapter);
        bj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id_To_Search = position + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Intent intent = new
                        Intent(getApplicationContext(),DisplayData.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }


    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent intent = new Intent(this, DisplayData.class);
            startActivity(intent);
        }
    }
}
