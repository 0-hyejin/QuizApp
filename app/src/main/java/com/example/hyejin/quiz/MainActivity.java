package com.example.hyejin.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final private int QUIZ_COUNT = 5;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"중국", "베이징", "자카르타", "마닐라", "스톡홀름"},
            {"인도", "뉴델리", "베이징", "방콕", "서울"},
            {"인도네시아", "자카르타", "마닐라", "뉴델리", "쿠알라룸푸르"},
            {"일본", "도쿄", "방콕", "타이베이", "자카르타"},
            {"태국", "방콕", "베를린", "아바나", "킹스턴"},
            {"브라질", "브라질리아", "아바나", "방콕", "코펜하겐"},
            {"캐나다", "오타와", "베른", "코펜하겐", "자카르타"},
            {"쿠바", "아바나", "베른", "런던", "멕시코시티"},
            {"멕시코", "멕시코시티", "오타와", "베를린", "산티아고"},
            {"미국", "워싱턴 D.C.", "새너제이", "부에노스 아이레스", "쿠알라룸푸르"},
            {"프랑스", "파리", "오타와", "코펜하겐", "도쿄"},
            {"독일", "베를린", "코펜하겐", "방콕", "산티아고"},
            {"이탈리아", "로마", "런던", "파리", "아테네"},
            {"스페인", "마드리드", "멕시코시티", "자카르타", "아바나"},
            {"영국", "런던", "로마", "파리", "싱가폴"}
    };
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countLabel = (TextView) findViewById(R.id.countLabel);
        questionLabel = (TextView) findViewById(R.id.questionLabel);
        answerBtn1 = (Button) findViewById(R.id.answerBtn1);
        answerBtn2 = (Button) findViewById(R.id.answerBtn2);
        answerBtn3 = (Button) findViewById(R.id.answerBtn3);
        answerBtn4 = (Button) findViewById(R.id.answerBtn4);



        for (int i = 0; i < quizData.length; i++) {

            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);       // 나라
            tmpArray.add(quizData[i][1]);       // 맞는 답
            tmpArray.add(quizData[i][2]);       // 선택1
            tmpArray.add(quizData[i][3]);       // 선택2
            tmpArray.add(quizData[i][4]);       // 선택3

            // Add tmpArray to quizArray
            quizArray.add(tmpArray);
        }
        showNextQuiz();


    }

    private void showNextQuiz() {
        //Update QuizCountLable 퀴즈카운트레이블 업데이트
        countLabel.setText("Q" + quizCount);

        //랜덤
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //하나의 퀴즈 세트 선택
        ArrayList<String> quiz = quizArray.get(randomNum);

        //문제랑 맞는 답
        //array format : {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //remove "country" from quiz shuffle choices
        quiz.remove(0);
        Collections.shuffle(quiz);

        //set choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //remove this quiz from quizarray
        quizArray.remove(randomNum);
    }


    public void checkAnswer(View view) {

        //버튼을 누를 때
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;


        if (btnText.equals(rightAnswer)) {
            //정답일때
            alertTitle = " 정답입니다 !!!";
            rightAnswerCount++;

        } else {
            //오답일때
            alertTitle = " 틀렸습니다 . . . ";
        }

        // 다이얼로그
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage(" 이 문제의 정답은  " + rightAnswer );
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    //결과
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();


    }

}
