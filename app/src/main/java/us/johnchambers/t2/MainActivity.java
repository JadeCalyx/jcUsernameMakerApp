package us.johnchambers.t2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

import android.view.inputmethod.InputMethodManager;


public class MainActivity extends AppCompatActivity {

    private EditText et;
    WordFilter _wordFilter = new WordFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.seedWord);
        //et.setHint("word");

        addListenerGoButton();
        addListenerKeyboard();
    }

    public void addListenerGoButton() {

        Button button = (Button) findViewById(R.id.go_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hideKeyboard();
                mr3();
            }
        });
    }

    public void addListenerKeyboard() {

        EditText et = (EditText) findViewById(R.id.seedWord);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    hideKeyboard();
                    mr3();
                }
                return true;
            }
        });
    }

    public void hideKeyboard() {
        View view = MainActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void mr3() {
        EditText seedWord = (EditText) findViewById(R.id.seedWord);
        if (_wordFilter.isBad(seedWord.getText().toString().toLowerCase())) {
            Toast.makeText(getApplicationContext(), "Unable to process word, try another.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Building List", Toast.LENGTH_SHORT).show();
            BaseWordGenerator bwg = new BaseWordGenerator((TextView) findViewById(R.id.unameText),
                    Volley.newRequestQueue(this),
                    seedWord.getText().toString());
            bwg.go();
        }
    }

}


