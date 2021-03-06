package com.example.caloriecheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.Model.Diary;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes extends AppCompatActivity {
    TextView tvday;
    EditText edcontent;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    String day,month,year,today;
    Button btnadd;
    ImageView imgnote,imgback;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        tvday = findViewById(R.id.dayinnotes);
        edcontent = findViewById(R.id.edcontentnotes);
        btnadd = findViewById(R.id.addnote);
        imgnote = findViewById(R.id.imginnotes);
        imgback = findViewById(R.id.imgbacknotes);
        Intent i= getIntent();
        getImagefromFirebase();
        Boolean check =  i.getBooleanExtra("check",false);
        if (check){
            tvday.setText(i.getStringExtra("dow")  + ", Ng??y " + i.getStringExtra("day") + " Th??ng "+ i.getStringExtra("month") );
            edcontent.setText(i.getStringExtra("content"));
        }else {
            getDay();
        }

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }else {
                        pickImageFromGallery();
                    }
                }else {
                    pickImageFromGallery();
                }
            }
        });



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edcontent.getText().toString().equals("")){
                    Toast.makeText(Notes.this,"N???i dung kh??ng ???????c ????? ch???ng",Toast.LENGTH_SHORT).show();

                }else {
                    getData();
                    Toast.makeText(Notes.this,"???? th??m",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }

    private String getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        day = sdf.format(new Date());
        month = sdf1.format(new Date());
        year = sdf3.format(new Date());
        today = sdf2.format(new Date());

        if(today.equals("Monday")){
            today = "Th??? Hai";
        }else if(today.equals("Tuesday")){
            today = "Th??? Ba";
        }else if(today.equals("Wednesday")){
            today = "Th??? T??";
        }else if(today.equals("Thursday")){
            today = "Th??? N??m";
        }else if(today.equals("Friday")){
            today = "Th??? S??u";
        }else if(today.equals("Saturday")){
            today = "Th??? B???y";
        }else if(today.equals("Sunday")){
            today = "Ch??? Nh???t";
        }


        tvday.setText(today + ", Ng??y " + day + " Th??ng "+month );
        return day  + month;
    }

    private void getData(){
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        String username = sharedPreferences.getString("username","");
        Diary diary = new Diary(day,month,year,today,edcontent.getText().toString());
        databaseReference.child(username).child("Notes").child(day +month).setValue(diary);
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Sellect Picture"),IMAGE_PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK){
            imgUri = data.getData();
            UploadImage(imgUri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                imgnote.setImageBitmap(bitmap);
                imgnote.setScaleType(ImageView.ScaleType.CENTER_CROP);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void UploadImage(Uri uris) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("??ang t???i");
        pd.show();
        String tvday;

        Intent i = getIntent();
        Boolean check =  i.getBooleanExtra("check",false);
        if (check){
              tvday = i.getStringExtra("day") + i.getStringExtra("month") ;

        }else {
             tvday =  getDay();
        }


        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + username + tvday + ".jpg");

        storageReference.putFile(uris)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(Notes.this,"Th??nh c??ng",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        pd.dismiss();
                        Toast.makeText(Notes.this,"Th???t b???i",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("??ang t???i: " + (int) progressPercent + "%");
                    }
                });
    }

    private void getImagefromFirebase(){
        String tvday;

        Intent i = getIntent();
        Boolean check =  i.getBooleanExtra("check",false);
        if (check){
            tvday = i.getStringExtra("day") + i.getStringExtra("month") ;

        }else {
            tvday =  getDay();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("User",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("images/" + username + tvday + ".jpg");

        try {
            final File localFile = File.createTempFile(username,"jpg");
            storage.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            imgnote.setImageBitmap(bitmap);
                            imgnote.setScaleType(ImageView.ScaleType.CENTER_CROP);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            Toast.makeText(Notes.this,"Th???t b???i",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}