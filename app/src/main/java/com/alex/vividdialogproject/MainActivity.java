package com.alex.vividdialogproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ornach.vividdialog.ButtonStyle;
import com.ornach.vividdialog.DialogInterface;
import com.ornach.vividdialog.VividCustomDialog;
import com.ornach.vividdialog.VividInputDialog;
import com.ornach.vividdialog.VividStandardDialog;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text);


        /*VividInputDialog dialog = new VividInputDialog.Builder(this)
                .setHeaderText("Customer Name Edit")
                .setHeaderBackgroundColor(Color.RED)
                .setMessage("Enter customer name")
//                .setIcon(R.drawable.ic_check_white_24dp)
                .setSubmitButtonListener("Save", new DialogInterface.OnSubmitListener() {
                    @Override
                    public void onSubmit(DialogInterface dialog, String text) {
                        Toast.makeText(MainActivity.this, "text: "+text, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        dialog.show();*/

        /*TextView textView1 = new TextView(this){{
            setText("This is test text");
            setBackgroundColor(Color.RED);
        }};

        VividCustomDialog dialog = new VividCustomDialog.Builder(this)
                .setHeaderText("Test Text")
                .setView(textView1)
                .build();
        dialog.show();*/
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonNormal:
                showNormalDialog();
                break;
            case R.id.buttonTheme:
                startActivity(new Intent(this, StandardDialogActivity.class));
                break;
            case R.id.buttonInput:
                showInputDialog();
                break;
            case R.id.buttonCustom:
                customDialog();
                break;


        }
    }

    private void showNormalDialog(){
        new VividStandardDialog.Builder(this)
                .setTitle("This is Title")
                .setMessage("this is simple text message. this is simple text message. ")
                .setAutoDismissible(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .build()
        .show();

    }

    private void showInputDialog(){
        VividInputDialog dialog = new VividInputDialog.Builder(this)
                .setHeaderText("Customer Name Edit")
                .setHeaderBackgroundColor(Color.BLUE)
                .setMessage("Enter customer name")
//                .setIcon(R.drawable.ic_check_white_24dp)
                .setSubmitButtonListener("Save", new DialogInterface.OnSubmitListener() {
                    @Override
                    public void onSubmit(DialogInterface dialog, String text) {
                        Toast.makeText(MainActivity.this, "text: "+text, Toast.LENGTH_SHORT).show();
                    }
                })
                .setSubmitButtonListener("Submit", new DialogInterface.OnSubmitListener() {
                    @Override
                    public void onSubmit(DialogInterface dialog, String text) {
                        Toast.makeText(MainActivity.this, "Input Text: "+ text, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        dialog.show();
    }

    private void customDialog(){

        VividCustomDialog dialog = new VividCustomDialog.Builder(this)
                .setView(R.layout.content_custom_dialog)
                .setHeaderText("Login Validation")
                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        View view = ((VividCustomDialog)dialog).getView();
                        String userName = ((TextView)view.findViewById(R.id.usernameText)).getText().toString();
                        String password = ((TextView)view.findViewById(R.id.passwordText)).getText().toString();

                        Toast.makeText(MainActivity.this, "Username: "+ userName+"\nPassword: "+ password, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .build();
        dialog.show();

    }



}
