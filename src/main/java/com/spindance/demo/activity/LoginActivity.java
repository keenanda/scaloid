package com.spindance.demo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import scaloid.example.R;

public class LoginActivity extends Activity {
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
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.dummy_login_url);

        final ProgressDialog dlg = ProgressDialog.show(this, null, getString(R.string.busy_login), true, true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dlg.dismiss();
                        Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, TodoListActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        queue.add(stringRequest);
    }
}