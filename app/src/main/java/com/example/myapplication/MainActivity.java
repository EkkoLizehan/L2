package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn;

    ImageView img;

    EditText display;
    EditText displays;
    String teamName = "";
    String postalCode = "";

    EditText teamNameInput;
    EditText postalCodeInput;

    Button postalCodeSubmitButton;

    //ImageButton profileDisplayButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displays = findViewById(R.id.editT1);
        //teamNameInput = (EditText) findViewById(R.id.editT1);
        //postalCodeInput = (EditText) findViewById(R.id.editT2);
        //postalCodeSubmitButton = (Button) findViewById(R.id.btn);
        //profileDisplayButton = (ImageButton) findViewById(R.id.imgT);
        //displayb = findViewById(R.id.editT2);
        btn = findViewById(R.id.btn);
        img = findViewById(R.id.imgT);

        displays.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(getString(R.string.display1).equals(displays.getText().toString())) {

                    displays.setText("");

                }
                //display.setText("");

            }

        });

        display = findViewById(R.id.editT2);

        display.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(getString(R.string.display2).equals(display.getText().toString())) {

                    display.setText("");

                }

            }

        });

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                OnOpenInGoogleMaps(v);

            }

        });

        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                OnSetAvatarButton(v);

            }
        });
    }


    public void OnOpenInGoogleMaps (View view){

        EditText teamAddress = (EditText) findViewById(R.id.editT2);
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + teamAddress.getText());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                        }
                }
            });

    public void OnSetAvatarButton (View view){

        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        profileActivityResultLauncher.launch(intent);

    }

    protected void onActivityResult (ActivityResult result){
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();

            ImageView avatarImage = (ImageView) findViewById(R.id.imgT);
            String drawableName;

            switch (data.getIntExtra("imageID", R.id.img1)) {
                case R.id.img1:
                    drawableName = "bulls";
                    break;
                case R.id.img2:
                    drawableName = "celtics";
                    break;
                case R.id.img3:
                    drawableName = "bucks";
                    break;
                case R.id.img4:
                    drawableName = "nets";
                    break;
                case R.id.img5:
                    drawableName = "lakers";
                    break;
                case R.id.img6:
                    drawableName = "clippers";
                    break;
                case R.id.img8:
                    drawableName = "rocket";
                    break;
                case R.id.img9:
                    drawableName = "raptors";
                    break;
                default:
                    drawableName = "warriors";
                    break;
            }
            int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
            avatarImage.setImageResource(resID);

        }
    }
}
