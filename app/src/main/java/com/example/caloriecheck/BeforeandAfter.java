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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class BeforeandAfter extends AppCompatActivity {
    ImageView before,after;
    private static final int IMAGE_PICK_BEFORE =1000;
    private static final int IMAGE_PICK_AFTER = 1002;
    private static final int PERMISSION_CODE = 1001;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beforeand_after);
        before = findViewById(R.id.imgbefore);
        after = findViewById(R.id.imgafter);

        getImageafterfromFirebase();
        getImagebeforefromFirebase();

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }else {
                        pickImageBeforeFromGallery();
                    }
                }else {
                    pickImageBeforeFromGallery();
                }
            }
        });

        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }else {
                        pickImageAfterFromGallery();
                    }
                }else {
                   pickImageAfterFromGallery();
                }
            }
        });
    }

    private void pickImageBeforeFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Sellect Picture"),IMAGE_PICK_BEFORE);
    }

    private void pickImageAfterFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Sellect Picture"),IMAGE_PICK_AFTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        if(requestCode == IMAGE_PICK_BEFORE && resultCode == RESULT_OK){
            imgUri = data.getData();
            UploadImageBefore(imgUri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                before.setImageBitmap(bitmap);
                before.setScaleType(ImageView.ScaleType.CENTER_CROP);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == IMAGE_PICK_AFTER && resultCode == RESULT_OK){
            imgUri = data.getData();
            UploadImageAfter(imgUri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                after.setImageBitmap(bitmap);
                after.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   pickImageBeforeFromGallery();
                }
                else {
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void UploadImageBefore(Uri uris){
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Đang tải");
        pd.show();

        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/before" + username + ".jpg");

        storageReference.putFile(uris)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(BeforeandAfter.this,"Thành công",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        pd.dismiss();
                        Toast.makeText(BeforeandAfter.this,"Thất bại",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Đang tải: " + (int) progressPercent + "%");
                    }
                });
    }

    private void UploadImageAfter(Uri uris){
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Đang tải");
        pd.show();

        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/after" + username + ".jpg");

        storageReference.putFile(uris)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(BeforeandAfter.this,"Thành công",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        pd.dismiss();
                        Toast.makeText(BeforeandAfter.this,"Thất bại",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Đang tải: " + (int) progressPercent + "%");
                    }
                });
    }

    private void getImagebeforefromFirebase(){
        SharedPreferences sharedPreferences = getSharedPreferences("User",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("images/before" + username + ".jpg");

        try {
            final File localFile = File.createTempFile(username,"jpg");
            storage.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            before.setImageBitmap(bitmap);
                            before.setScaleType(ImageView.ScaleType.CENTER_CROP);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            Toast.makeText(BeforeandAfter.this,"Thất bại",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getImageafterfromFirebase(){
        SharedPreferences sharedPreferences = getSharedPreferences("User",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("images/after" + username + ".jpg");

        try {
            final File localFile = File.createTempFile(username,"jpg");
            storage.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            after.setImageBitmap(bitmap);
                            after.setScaleType(ImageView.ScaleType.CENTER_CROP);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            Toast.makeText(BeforeandAfter.this,"Thất bại",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}