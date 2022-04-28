package com.example.caloriecheck.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.BeforeandAfter;
import com.example.caloriecheck.Editprofile;
import com.example.caloriecheck.Login;
import com.example.caloriecheck.MainActivity;
import com.example.caloriecheck.R;
import com.example.caloriecheck.learn2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;


public class InfoFragment extends Fragment {
    SharedPreferences sharedPreferences,preferences;
    TextView siandsu,tvSaveprofile,tvsua,tvlogout,tvgoal,tvweight,tvgoalweight,tvcalorie,tvbaa,tv52,tvtitlebaa;
    LinearLayout logout,changePass;
    String muctieu;
    ImageView avatar;
    private static final int IMAGE_PICK_CODE =1000;
    private static final int PERMISSION_CODE = 1001;
    Uri imgUri;
    ProgressBar load,pBbaa;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_info, container, false);
         siandsu = view.findViewById(R.id.tvsignuporlogin);
         tvSaveprofile = view.findViewById(R.id.tvsaveprofile);
         logout = view.findViewById(R.id.linearLayoutlogout);
         tvsua = view.findViewById(R.id.tvsuaininfo);
         changePass = view.findViewById(R.id.Layoutchangepassword);
         tvlogout = view.findViewById(R.id.tvinlayoutlogout);
         tvbaa = view.findViewById(R.id.tvbeforeandafter);
         tvgoal = view.findViewById(R.id.tvgoalininfofragment);
         tvweight = view.findViewById(R.id.tvweightininfofragment);
         tvgoalweight = view.findViewById(R.id.tvgoalweightininfofragment);
         tvcalorie = view.findViewById(R.id.tvCalorieininfofragment);
         avatar = view.findViewById(R.id.imgAvatar);
         load = view.findViewById(R.id.loadimg);
         tv52 = view.findViewById(R.id.textView52);
         tvtitlebaa = view.findViewById(R.id.tvbaatitle);
         pBbaa = view.findViewById(R.id.progressBarbaa);


       
        sharedPreferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        preferences = getContext().getSharedPreferences("infomation",Context.MODE_PRIVATE);
        int mt = preferences.getInt("nhucau",0);

        int canbd = preferences.getInt("canbd",0);
        int canht = preferences.getInt("can",0);
        int canmt = preferences.getInt("canmoi",0);
        pBbaa.setMax(Math.abs(canmt -canbd));
        if(canht == canmt){
            tvtitlebaa.setText("Chúc mừng bạn đã hoàn thành mục tiêu");
            pBbaa.setProgress(  Math.abs(canmt-canbd) );
        }else if(canbd == canht) {
            tvtitlebaa.setText("Bạn sẽ làm được thôi");
            pBbaa.setProgress(0);
        }else {
            tvtitlebaa.setText("Bạn đã giảm được " + Math.abs(canht - canbd) + " kg");
            pBbaa.setProgress(Math.abs(canht - canbd));
        }


        if(mt == -500){
            muctieu="Giảm cân";
        }else if(mt == 0){
            muctieu="Giữ cân";
        }else muctieu="Tăng cân";

        tvgoal.setText("-Mục tiêu: " + muctieu);
        tvweight.setText("-Cân nặng hiện tại: "+preferences.getInt("can",0)+" kg");
        tvgoalweight.setText("-Cân nặng mong muốn: "+preferences.getInt("canmoi",0)+" kg");
        tvcalorie.setText("-Calorie trong ngày: " + preferences.getInt("calorie",0)+" kcal");
        Boolean checklogin = sharedPreferences.getBoolean("checklogin",false);

        if (checklogin){
            getImagefromFirebase();
            siandsu.setVisibility(View.GONE);
            tvSaveprofile.setText("Xin chào " + sharedPreferences.getString("name",""));
            tvlogout.setText("Đăng xuất");
        }
//
//        tv52.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getContext(), learn2.class);
//                startActivity(i);
//            }
//        });

        tvbaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checklogin){
                    Intent i = new Intent(getContext(), BeforeandAfter.class);
                    startActivity(i);
                }else {
                    AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn cần đăng nhập để tiếp tục?")
                            .setCancelable(false)
                            .setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getContext(), Login.class);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("Để sau",null);
                    builder.show();
                }

            }
        });


        siandsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Login.class);
                startActivity(i);
            }
        });

        tvsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Editprofile.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvlogout.getText().toString().equals("Đăng xuất")){
                    setLogout();
                }else if(tvlogout.getText().toString().equals("Xóa dữ liệu")){
                    setdeletedata();
                }
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
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

        
        return view;
    }

    private void getImagefromFirebase(){
        sharedPreferences = getContext().getSharedPreferences("User",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("images/" + username + ".jpg");

        try {
            final File localFile = File.createTempFile(username,"jpg");
            storage.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            avatar.setImageBitmap(bitmap);
                            load.setVisibility(View.GONE);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            load.setVisibility(View.GONE);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Sellect Picture"),IMAGE_PICK_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_PICK_CODE && resultCode == getActivity().RESULT_OK){
            imgUri = data.getData();
            UploadImage(imgUri);
        }


        try {
           Bitmap  bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imgUri);
            avatar.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(getContext(),"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void UploadImage(Uri uris){
        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Đang tải");
        pd.show();

        sharedPreferences = getContext().getSharedPreferences("User",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + username + ".jpg");

        storageReference.putFile(uris)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getContext(),"Thành công",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        pd.dismiss();
                        Toast.makeText(getContext(),"Thất bại",Toast.LENGTH_SHORT).show();
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

    private void setLogout(){
        AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có chắc muốn đăng xuất không?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().clear().apply();
                        preferences.edit().clear().apply();
                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Không",null);
        builder.show();
    }

    private void setdeletedata(){
        AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có chắc muốn xóa dữ liệu không?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences.edit().clear().apply();

                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Không",null);
        builder.show();
    }
}