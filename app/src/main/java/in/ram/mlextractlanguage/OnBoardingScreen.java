package in.ram.mlextractlanguage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("Extract Text From Images Using Machine Learning")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.obboard) // int top drawable
                .setSummary("You can use ML kit to recognize text in images or videos")
                .build());

        addFragment(new Step.Builder().setTitle("Copy and Use the Extracted Text in Seconds")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable FD0956
                .setSummary("You can use ML kit to recognize text in images or videos")
                .build());

        addFragment(new Step.Builder().setTitle("Paste the Extracted text easily")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.ttospeach) // int top drawable
                .setSummary("You can use ML kit to recognize text in images or videos")
                .build());
    }


    @Override
    public void finishTutorial() {
        Intent i =new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}