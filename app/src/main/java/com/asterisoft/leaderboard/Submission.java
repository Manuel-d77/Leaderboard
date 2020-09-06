package com.asterisoft.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.asterisoft.leaderboard.services.MainService;
import com.asterisoft.leaderboard.services.ServiceBuilder;

public class Submission extends AppCompatActivity {
    private EditText fname, lname, email, link;
    private Toolbar toolbar;
    private Button submit;
    String name, lastName, e_mail, gitLink;
    private boolean isValid = false;
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static final String FORM_ID = "1FAlpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjlhaMAz8WChQ/formResponse";
    private static final String FULL_URL = BASE_URL + FORM_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        toolbar = findViewById(R.id.submission_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.gads);
        fname = findViewById(R.id.first_name_textView);
        lname = findViewById(R.id.last_name_textView);
        email = findViewById(R.id.email_textView);
        link = findViewById(R.id.git_textView);
        submit = findViewById(R.id.submission_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    displayDialog();
                }
            }
        });
    }

    private void getFormEntries() {
        name = fname.getText().toString();
        lastName = lname.getText().toString();
        e_mail = email.getText().toString();
        gitLink = link.getText().toString();
    }

    private boolean validateForm() {
        getFormEntries();
        if (!isValid) {
            if (name.isEmpty() || name == "") {
                fname.setError("First Name is required");
                fname.requestFocus();
                isValid = false;
            } else if (lastName.isEmpty() || lastName == "") {
                lname.setError("Last name is required");
                lname.requestFocus();
                isValid = false;
            } else if (e_mail.isEmpty() || e_mail == "") {
                email.setError("Email is required");
                email.requestFocus();
                isValid = false;
            } else if (gitLink.isEmpty() || gitLink == "") {
                link.setError("Project on GitHub  is required");
                link.requestFocus();
                isValid = false;
            } else {
                isValid = true;
            }
        }
        return isValid;
    }

    private void displayDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_design);
        Button yesButton;
        ImageButton cancelButton;
        yesButton = dialog.findViewById(R.id.yes_button);
        cancelButton = dialog.findViewById(R.id.cancel_imageButton);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainService formService= ServiceBuilder.buildService(MainService.class);
                Call<Void> submitForm= formService.submitForm(
                        FULL_URL,
                        e_mail,
                        name,
                        lastName,
                        gitLink
                );
                submitForm.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dialog.setContentView(R.layout.dialog_success);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show(); clearForm();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.setContentView(R.layout.dialog_failed);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                    }
                });
            }
        });
    }

    private void clearForm() {
        fname.setText("");
        lname.setText("");
        email.setText("");
        link.setText("");
    }
}

