package com.alex.vividdialogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ornach.vividdialog.ButtonStyle;
import com.ornach.vividdialog.DialogInterface;
import com.ornach.vividdialog.VividStandardDialog;

public class StandardDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_dialog);
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
