package dushyant.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "dushyant.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "dushyant.android.geoquiz.answer_shown";

    private static final String KEY_ANSWER_SHOWN = "answer_shown";

    private boolean mAnswerIsTrue;
    private boolean mAnswerShown;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private TextView mAPITextView;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, boolean isCheater) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        i.putExtra(EXTRA_ANSWER_SHOWN, isCheater);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerShown = getIntent().getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
        if (savedInstanceState != null) {
            mAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN);
            setAnswerShownResult();
        }

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resId = (mAnswerIsTrue) ? R.string.true_button : R.string.false_button;
                mAnswerTextView.setText(resId);
                mAnswerShown = true;
                setAnswerShownResult();
            }
        });

        mAPITextView = (TextView) findViewById(R.id.api_text_view);
        mAPITextView.setText("API " + Build.VERSION.SDK_INT);
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN, mAnswerShown);
    }
}
