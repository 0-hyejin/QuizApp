package com.example.hyejin.quiz;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StudyActivity extends AppCompatActivity {
    int imgNum = 1; // 보여줄 이미지 번호
    ImageView img; // 이미지 보여주는 위젯
    Button btn; // 나라 이름 보여주는 위젯
    Resources res; // 리소스 관리 객체

    private void changeImg() { // 현재 imgNum에 해당하는 이미지를 가져와서 보여줌
        int id = res.getIdentifier("img" + imgNum, "drawable", getPackageName());
        img.setImageResource(id);
    }

    private void changePlace() { // 현재 imgNum에 해당하는 나라이름을 가져와서 보여줌
        int id = res.getIdentifier("place" + imgNum, "string", getPackageName());
        String place = res.getString(id);
        btn.setText(place);
    }

    public void changeImgLeft(View v) { //왼쪽 화살표를 클릭하면 실행
        if(imgNum > 1) {
            imgNum--;
            changeImg();
            changePlace();
        }
    }

    public void changeImgRight(View v) { // 오른쪽 화살표를 클릭하면 실행
        if(imgNum < 10) {
            imgNum++;
            changeImg();
            changePlace();
        }
    }

    public void viewAllRest(View v) { // '맛집기행'을 클릭하면 실행 : 모든 맛집이름을 보여줌
        int id1= res.getIdentifier("place1", "string", getPackageName());
        String place1 = res.getString(id1);
        int id2= res.getIdentifier("place2", "string", getPackageName());
        String place2 = res.getString(id2);
        int id3= res.getIdentifier("place3", "string", getPackageName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        img = (ImageView)findViewById(R.id.imageView); // 이미지를 보여주는 위젯
        btn = (Button)findViewById(R.id.button); // 이름을 보여주는 위젯
        res = getResources(); // 리소스 관리 객체



    }
}
