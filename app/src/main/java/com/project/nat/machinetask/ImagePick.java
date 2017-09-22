package com.project.nat.machinetask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by csuthar on 21/08/17.
 */

public class ImagePick  extends AppCompatActivity{


    private ImageView imageView;
    public final int SELECT_PHOTO=1;
    private String TAG="ImagePick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pick);
        initialise();
    }

    private void initialise() {
        imageView=(ImageView)findViewById(R.id.image);
    }


//    public static void  screenTo(){
//        Intent intent =new Intent(this,ImagePick.class);
//        startActivity(intent);
//
//
//    }


    public void imageCapture(View view){


    }

    public void imagePick(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){

                        try {
                            final Uri imageUri = imageReturnedIntent.getData();
                            Log.e(TAG, "imageUri: "+imageUri );
                            final InputStream imageStream;
                            imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            Log.e(TAG, "selectedImage: "+selectedImage.toString());
                            imageView.setImageBitmap(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                }
        }
    }

}
