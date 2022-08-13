package com.amaromerovic.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.amaromerovic.quizapp.databinding.ActivityMainBinding;
import com.amaromerovic.quizapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Snackbar snackbar;
    private int questionIndex;
    private ActivityMainBinding mainBinding;
    private final Question[] questions = new Question[]{
            new Question(1, R.string.question1, true), new Question(2, R.string.question2, false),
            new Question(3, R.string.question3, true), new Question(4, R.string.question4, false),
            new Question(5, R.string.question5, true), new Question(6, R.string.question6, true),
            new Question(7, R.string.question7, true), new Question(8, R.string.question8, true),
            new Question(9, R.string.question9, false), new Question(10, R.string.question10, true),
            new Question(11, R.string.question11, true), new Question(12, R.string.question12, false),
            new Question(13, R.string.question13, false), new Question(14, R.string.question14, false),
            new Question(15, R.string.question15, false), new Question(16, R.string.question16, true),
            new Question(17, R.string.question17, false), new Question(18, R.string.question18, false),
            new Question(19, R.string.question19, true), new Question(20, R.string.question20, false),
            new Question(21, R.string.question21, true), new Question(22, R.string.question22, true),
            new Question(23, R.string.question23, true), new Question(24, R.string.question24, false),
            new Question(25, R.string.question25, true), new Question(26, R.string.question26, false),
            new Question(27, R.string.question27, false), new Question(28, R.string.question28, false),
            new Question(29, R.string.question29, true), new Question(30, R.string.question30, true),
            new Question(31, R.string.question31, false), new Question(32, R.string.question32, false),
            new Question(33, R.string.question33, true), new Question(34, R.string.question34, false),
            new Question(35, R.string.question35, true), new Question(36, R.string.question36, true),
            new Question(37, R.string.question37, true), new Question(38, R.string.question38, true),
            new Question(39, R.string.question39, true), new Question(40, R.string.question40, true),
            new Question(41, R.string.question41, false), new Question(42, R.string.question42, true),
            new Question(43, R.string.question43, true), new Question(44, R.string.question44, false),
            new Question(45, R.string.question45, true), new Question(46, R.string.question46, true),
            new Question(47, R.string.question47, true), new Question(48, R.string.question48, true),
            new Question(49, R.string.question49, false), new Question(50, R.string.question50, false),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        questionIndex = 0;
        updateQuestion();

        alert("Welcome to the quiz app, pick an answer by pressing \"True\" or \"False\", then press \"Next\" to continue, or you can also press \"Previous\" and retake a question. After you go trough all of the questions, you will have the option to retake the entire quiz. Don't feel bad if u don't do too well, make sure to have fun and learn in the process!", "Manual");



        mainBinding.nextButton.setOnClickListener(view -> {
            questionIndex++;
            if (questionIndex == (questions.length - 1)) {
                alert("You are on the last question, if you press \"Retake\" the quiz will be retaken and you will be back to question #1!", "Information");
                mainBinding.nextButton.setText(R.string.nextToRetakeButton);
            } else if (questionIndex == questions.length) {
                questionIndex = 0;
                mainBinding.nextButton.setText(R.string.nextButtonText);
            }
            updateQuestion();
        });

        mainBinding.previousButton.setOnClickListener(view -> {
            questionIndex--;
            if (questionIndex < 0) {
                questionIndex = 0;
                show("You are on the first question!");
                return;
            } else if (questionIndex == (questions.length - 2)){
                mainBinding.nextButton.setText(R.string.nextButtonText);
            }
            updateQuestion();
        });

        mainBinding.trueButton.setOnClickListener(view -> processResult(true));

        mainBinding.falseButton.setOnClickListener(view -> processResult(false));

    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion() {
        mainBinding.questionNumberView.setText(questions[questionIndex].getQuestionNumber() + "/" + questions.length);
        mainBinding.questionTextView.setText(questions[questionIndex].getQuestionText());
    }


    private void show(String message) {
        if (snackbar != null) {
            snackbar.dismiss();
        }
        snackbar = Snackbar.make(this.mainBinding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.setDuration(Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (tv != null) {
            tv.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        }
        snackbar.show();
    }

    private void alert(String message, String title){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("I Understand", (dialog1, id) -> {
            //Action
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }

    public void processResult(boolean userOption){
        int text = R.string.incorrectAnswer;
        int color = ContextCompat.getColor(MainActivity.this, R.color.myRedColor);

        if (questions[questionIndex].getAnswer() == userOption){
            text = R.string.correctAnswer;
            color = ContextCompat.getColor(MainActivity.this, R.color.myGreenColor);
        }

        if (snackbar != null)
        snackbar.dismiss();
        snackbar = Snackbar.make(this.mainBinding.getRoot(), text, Snackbar.LENGTH_SHORT).setBackgroundTint(color);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.CENTER;
        params.width=FrameLayout.LayoutParams.WRAP_CONTENT;
        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        if(tv!=null) {
            tv.setGravity(Gravity.CENTER);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        }
        view.setLayoutParams(params);
        snackbar.show();
    }




}