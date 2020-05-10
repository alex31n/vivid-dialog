package com.alex.vividdialogproject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ornach.vividdialog.ButtonStyle;
import com.ornach.vividdialog.DialogInterface;
import com.ornach.vividdialog.VividInputDialog;
import com.ornach.vividdialog.VividStandardDialog;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text);


        VividInputDialog dialog = new VividInputDialog.Builder(this)
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

        dialog.show();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonNormal:
                showDialog(VividStandardDialog.ThemeType.NORMAL);
                break;
            case R.id.buttonSuccess:
                showDialog(VividStandardDialog.ThemeType.SUCCESS);
                break;
            case R.id.buttonWarning:
                showDialog(VividStandardDialog.ThemeType.WARNING);
                break;
            case R.id.buttonInfo:
                showDialog(VividStandardDialog.ThemeType.INFORMATION);
                break;
            case R.id.buttonDark:
                showDialog(VividStandardDialog.ThemeType.DARK);
                break;
            case R.id.buttonCustom:

                customDialog();
                break;


        }
    }

    private void showDialog(VividStandardDialog.ThemeType themeType){
        new VividStandardDialog.Builder(this)
                .setThemeType(themeType)
                .setTitle("This is Title")
                .setMessage("this is simple text message. this is simple text message. ")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

    private void customDialog(){

        new VividStandardDialog.Builder(this)
                .setHeaderBackgroundColor(Color.BLUE)
                .setHeaderTextColor(Color.YELLOW)
                .setIcon(R.drawable.ic_close_white_24dp)
                .setIconColor(Color.RED)
                .setBackgroundColorRes(R.color.colorAccent)
                .setTextColor(Color.WHITE)
                .setTitle("This is Title")
                .setMessage("this is simple text message. this is simple text message. ")
                .setNegativeButtonStyle(new ButtonStyle(){{
                    setBackgroundColor(Color.BLACK);
                    setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                    setBorderColor(Color.YELLOW);
                }})
                .setPositiveButtonStyle(
                        new ButtonStyle()
                        .setTextColor(Color.BLACK)
                        .setBackgroundColor(Color.WHITE)
                        .setBorderColor(Color.GREEN)
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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



}
