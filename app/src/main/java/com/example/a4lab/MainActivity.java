package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Сделать работу с фотографией

    final String NAMEVALUE = "namePerson";
    final  String SURNAMEVALUE = "surnamePerson";
    final String AGEVALUE = "agePerson";
    final String CURSEVALUE = "cursePerson";
    final String EMAILVALUE = "emailPerson";
    final String PHONEVALUE = "phonePerson";
    final String NETWORKVALUE = "networkPerson";
    final String PHOTOVALUE = "photoPerson";

    EditText name,surname,age,curse,email,phone,networkReference;
    ImageView photo;
    Uri photoUri;

    private final int Pick_image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameId);
        surname = (EditText) findViewById(R.id.surnameId);
        age = (EditText) findViewById(R.id.ageId);
        curse = (EditText) findViewById(R.id.curseId);
        email = (EditText) findViewById(R.id.emailId);
        phone = (EditText) findViewById(R.id.phoneId);
        networkReference = (EditText) findViewById(R.id.networkId);
        photo = (ImageView) findViewById(R.id.photoId);

        Button button = (Button) findViewById(R.id.setPicture);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Pick_image);
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, Pick_image);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);


       /* if (requestCode == Pick_image) {
            Bitmap thumbnail = (Bitmap) imageReturnedIntent.getExtras().get("data");
            ImageView ivCamera = (ImageView) findViewById(R.id.photoId);
            ivCamera.setImageBitmap(thumbnail);
        }*/

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    try {

                        //Получаем URI изображения, преобразуем его в Bitmap
                        //объект и отображаем в элементе ImageView нашего интерфейса:
                        final Uri imageUri = imageReturnedIntent.getData();
                        photoUri = imageUri;
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        photo.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    public void nextClick(View view) {
        EditText edit  = (EditText) findViewById(R.id.nameId);
        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        intent.putExtra(NAMEVALUE,name.getText().toString());
        intent.putExtra(SURNAMEVALUE,surname.getText().toString());
        intent.putExtra(AGEVALUE,age.getText().toString());
        intent.putExtra(CURSEVALUE,curse.getText().toString());
        intent.putExtra(EMAILVALUE,email.getText().toString());
        intent.putExtra(PHONEVALUE,phone.getText().toString());
        intent.putExtra(NETWORKVALUE,networkReference.getText().toString());
        intent.putExtra(PHOTOVALUE,photoUri);
        startActivity(intent);
    }
}