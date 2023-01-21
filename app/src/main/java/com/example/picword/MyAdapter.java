package com.example.picword;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MyAdapter extends BaseAdapter {
    MainActivity mainActivity;
    List<Integer> list;
    int click=1,pos1=0,pos2=0;
    Runnable runnable;
    View firstview;


    public MyAdapter(MainActivity mainActivity, List<Integer> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=LayoutInflater.from(mainActivity).inflate(R.layout.item,parent,false);
        ImageView imageView=convertView.findViewById(R.id.img);
        imageView.setImageResource(list.get(position));

        View view1=convertView.findViewById(R.id.view1);
        RelativeLayout relativeLayout=convertView.findViewById(R.id.relative);
          int interval = 2000; // 1 Second
         Handler handler = new Handler();
          runnable = new Runnable(){
            public void run() {
                view1.setVisibility(View.VISIBLE);
            }
        };
        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);

        relativeLayout.setOnClickListener(v -> {

              if(click==1)
              {
                  view1.setVisibility(View.INVISIBLE);
                  pos1=position;
                  firstview=view1;
                  click=3;
                  runnable = new Runnable(){
                      public void run() {
                          click=2;
                      }
                  };
                  handler.postAtTime(runnable, System.currentTimeMillis()+interval);
                  handler.postDelayed(runnable, 100);
                  System.out.println("first click");
              }
              if(click==2){
                  view1.setVisibility(View.INVISIBLE);
                  pos2=position;
                  click=3;
                  System.out.println("second click");
                  if(list.get(pos1).equals(list.get(pos2)))
                  {
                      System.out.println("match");
                      runnable = new Runnable(){
                          public void run() {
                              click=1;
                          }
                      };
                      handler.postAtTime(runnable, System.currentTimeMillis()+interval);
                      handler.postDelayed(runnable, 500);
                  }
                  else {
                      System.out.println("not match");
                      runnable = new Runnable(){
                          public void run() {
                              view1.setVisibility(View.VISIBLE);
                              firstview.setVisibility(View.VISIBLE);
                              click=1;
                          }
                      };
                      handler.postAtTime(runnable, System.currentTimeMillis()+interval);
                      handler.postDelayed(runnable, 500);

                  }
              }

        });


        return convertView;
    }
}
