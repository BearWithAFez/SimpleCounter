package dwightvdv.simplecounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CounterActivity extends AppCompatActivity {
    /**
        There is a small, 1x1, editText called etHide that is hidden to change focus to,
        It is not ideal, at all, but its the best workaround I have at the time.
     */

    // Java
    private Counter counter;
    private Toast goalToast;
    // UI
    private EditText etGoal;
    private EditText etTitle;
    private Button btnCnt;
    private EditText etHide;
    // Data
    private SharedPreferences sp;
    private SharedPreferences.Editor sp_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initiating
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        counter = new Counter();

        // Toast
        goalToast = Toast.makeText(getApplicationContext(),"Goal achieved!",Toast.LENGTH_LONG);

        // Ui linking
        etGoal = this.findViewById(R.id.etGoal);
        etTitle = this.findViewById(R.id.etTitle);
        btnCnt = this.findViewById(R.id.btnCount);
        etHide = this.findViewById(R.id.hideFocus);

        // Events
        etGoal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                counter.setGoal(Integer.parseInt("0" + etGoal.getText().toString()));
            }
        });
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                counter.setName(etTitle.getText().toString());
            }
        });

        // Create data connection
        sp = getSharedPreferences("data", 0);
        sp_e = sp.edit();

        // Read
        if(sp.contains("name")){
            counter.setName(sp.getString("name",""));
            counter.setGoal(sp.getInt("goal",0));
            counter.setCount(sp.getInt("cnt",0));
        }

        // Finally update GUI
        refresh();
    }

    public void onButtonResetTap(View v){
        counter.reset();
        refresh();
    }

    public void onButtonRevertTap(View v){
        counter.revert();
        refresh();
    }

    public void onButtonIncreaseTap(View v){
        counter.increment();
        refresh();
    }

    public void onBackgroundTap (View v){
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        // Hide Focus
        etHide.requestFocus();
    }

    private void refresh() {
        // Update visuals (Goal and count)
        btnCnt.setText("" + counter.getCount());
        etGoal.setText("" + counter.getGoal());
        etTitle.setText("" + counter.getName());

        // Display a congrats if needed
        if(counter.isGoal()) goalToast.show();

        // Save
        sp_e.putString("name", counter.getName());
        sp_e.putInt("goal", counter.getGoal());
        sp_e.putInt("cnt", counter.getCount());

        // Commit
        sp_e.commit();
    }
}
