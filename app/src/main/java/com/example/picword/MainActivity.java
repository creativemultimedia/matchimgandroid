package com.example.picword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    int[] imgarr={
            R.drawable.aguacate,R.drawable.ajo,R.drawable.ardilla,R.drawable.barco,R.drawable.bombon,R.drawable.bellota,
            R.drawable.aguacate,R.drawable.ajo,R.drawable.ardilla,R.drawable.barco,R.drawable.bombon,R.drawable.bellota
    };
    List<Integer> list;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.grid);

        list = Arrays.stream(imgarr).boxed().collect(Collectors.toList());
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        MyAdapter myAdapter=new MyAdapter(this,list);
        gridView.setAdapter(myAdapter);
    }
}