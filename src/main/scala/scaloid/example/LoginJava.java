package scaloid.example;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginJava extends Activity {
    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        mEmailText = (EditText)findViewById(R.id.form_login_email);
        mPasswordText = (EditText)findViewById(R.id.form_login_password);
        mLoginButton = (Button)findViewById(R.id.form_login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });
    }


    private void loginClicked() {
        Toast.makeText(this,
                "Login with " + mEmailText.getText().toString() + " and " + mPasswordText.getText().toString(), Toast.LENGTH_SHORT)
                .show();
    }
}
