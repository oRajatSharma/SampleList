package com.example.rajat.samplelist;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends Activity implements Animation.AnimationListener {

    private static final String TAG = "Main2Activity";
    public static final int DURATION = 5000;
    TextView textToAnim;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textToAnim = (TextView) findViewById(R.id.text_to_anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.view_anim);

        anim.setAnimationListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            textToAnim.startAnimation(anim);
        }
    }

    // View Animation listeners
    @Override
    public void onAnimationStart(Animation animation) {}

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == anim) {
            Toast.makeText(this, "View Animation Ended", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.value_animator:
                Log.d(TAG, "Value Animator");
                startValueAnimator();
                return true;
            case R.id.object_animator:
                Log.d(TAG, "Object Animator");
                startValueAnimator();
                startObjectAnimation();
                return true;

            case R.id.view_property_animator:
                Log.d(TAG, "Object Animator");
                startValueAnimator();
                startObjectAnimation();
                startViewPropertyAnimator();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Value Animation
    private void startValueAnimator() {
        ValueAnimator animation = ValueAnimator.ofInt(0, 180);
        animation.setInterpolator(new BounceInterpolator());
        animation.setDuration(DURATION);
        animation.addUpdateListener(new valueAnimListener());
        animation.start();
    }

    class valueAnimListener implements ValueAnimator.AnimatorUpdateListener {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int i = (int) valueAnimator.getAnimatedValue();
            Log.d(TAG, "Value = " + i);
            TextView textView = (TextView) findViewById(R.id.value_anim);
            textView.setText(Integer.toString(i));
          textView.setRotation(i);
        }
    }




    private void startObjectAnimation() {
        TextView textView = (TextView) findViewById(R.id.object_anim);
        ObjectAnimator anim = ObjectAnimator.ofFloat(textView, "rotation", 0, 180);
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0.4f);
        AnimatorSet as = new AnimatorSet();
        as.playTogether(anim, animAlpha);
        as.setDuration(DURATION);
        as.setInterpolator(new BounceInterpolator());
        as.start();
    }






    private void startViewPropertyAnimator() {
        TextView textView = (TextView) findViewById(R.id.view_property_anim);
        textView.animate().rotation(180).alpha(0.4f).setDuration(DURATION).setInterpolator(new BounceInterpolator());
    }


}
