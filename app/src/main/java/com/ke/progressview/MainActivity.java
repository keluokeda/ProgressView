package com.ke.progressview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ke.library.ProgressView;

public class MainActivity extends AppCompatActivity {
    private ProgressView mProgressView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressView = (ProgressView) findViewById(R.id.progress_view);
        mTextView = (TextView) findViewById(R.id.textView);

    }

    public void start(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mProgressView, "progress", 0, 80);
        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimator.setDuration(1000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.setText(String.valueOf(value));
            }
        });
        objectAnimator.start();

    }
}
