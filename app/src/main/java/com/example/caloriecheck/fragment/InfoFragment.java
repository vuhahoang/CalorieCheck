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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.Activitiy.BeforeandAfter;
import com.example.caloriecheck.Activitiy.Editprofile;
import com.example.caloriecheck.Activitiy.Login;
import com.example.caloriecheck.Activitiy.MainActivity;
import com.example.caloriecheck.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class InfoFragment extends Fragment {
    SharedPreferences sharedPreferences,preferences,preferencesTimeChallenge;
    TextView siandsu,tvSaveprofile,tvsua,tvlogout,tvgoal,tvweight,tvgoalweight,tvcalorie,tvbaa,tv52,tvtitlebaa,tvcaidat,tvgiay,tvchallenge,tvphut,tvgio,tvngay,tvnamechallenge,tvgiveup;
    LinearLayout logout,changePass,lnosugar,lnosocola,lnofastfood,lnocaffe,lnoalcoho,lnomeat,lnocigarette;
    String muctieu;
    ImageView avatar;
    private static final int IMAGE_PICK_CODE =1000;
    private static final int PERMISSION_CODE = 1001;
    Uri imgUri;
    ProgressBar load,pBbaa;
    CardView cvChallenge;
    HorizontalScrollView hschallenge;
    int count = 0;
    int countMinute = 0;
    int countHour = 0;
    int countDay = 0;
    Timer T;



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
         lnosugar = view.findViewById(R.id.chalnosugar);
         cvChallenge = view.findViewById(R.id.layoutchallenge);
         hschallenge = view.findViewById(R.id.horizontalScrollView3);
         tvcaidat = view.findViewById(R.id.textView26);
         tvgiay = view.findViewById(R.id.tvsecond);
         tvchallenge = view.findViewById(R.id.textView60);
         tvphut = view.findViewById(R.id.tvMinutechallenge);
         tvgio = view.findViewById(R.id.tvhourchallenge);
         tvngay= view.findViewById(R.id.tvdaychallenge);
         lnosocola = view.findViewById(R.id.chalnosocola);
         lnofastfood = view.findViewById(R.id.chalnofastfood);
         lnoalcoho = view.findViewById(R.id.chalnoalcohol);
         lnocaffe = view.findViewById(R.id.chalnocoffe);
         lnomeat = view.findViewById(R.id.chalnomeat);
         lnocigarette = view.findViewById(R.id.chalnocigarette);
         tvnamechallenge = view.findViewById(R.id.textView55);
         tvgiveup = view.findViewById(R.id.tvgiveupChallenge);


       
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

        lnosugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không đường");

                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        lnosocola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không Socola");

                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        lnofastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không đồ ăn nhanh");

                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        lnocaffe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không cà phê");

                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        lnoalcoho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không rượu bia");

                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        lnomeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không thịt");

                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        lnocigarette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChallenge();
                tvnamechallenge.setText("Không thuốc lá");
                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
                editor.putBoolean("checkhave",true);
                editor.commit();
            }
        });

        tvgiveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có chắc muốn dừng thử thách không?")
                        .setCancelable(false)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                                preferencesTimeChallenge.edit().clear().apply();
                                T.cancel();
                                count =0;
                                countDay = 0;
                                countHour = 0;
                                countMinute = 0;
                                cvChallenge.setVisibility(View.GONE);
                                hschallenge.setVisibility(View.VISIBLE);
                                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tvcaidat.getLayoutParams();
                                params.topToBottom = R.id.horizontalScrollView3;
                                tvcaidat.setLayoutParams(params);

                            }
                        })
                        .setNegativeButton("Không",null);
                builder.show();
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
                        preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                        sharedPreferences.edit().clear().apply();
                        preferences.edit().clear().apply();
                        preferencesTimeChallenge.edit().clear().apply();
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
                        preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
                        preferences.edit().clear().apply();
                        preferencesTimeChallenge.edit().clear().apply();

                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Không",null);
        builder.show();
    }

    private void setChallenge(){
        cvChallenge.setVisibility(View.VISIBLE);
        hschallenge.setVisibility(View.GONE);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tvcaidat.getLayoutParams();
        params.topToBottom = R.id.layoutchallenge;
        tvcaidat.setLayoutParams(params);
        settimestart();
    }

    private void settimestart(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
        String timenow = sdf.format(new Date());


        preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);

        Boolean check = preferencesTimeChallenge.getBoolean("checkhave",false);
//
        String timeshut = preferencesTimeChallenge.getString("timeshut",timenow);
        Log.d("time",timenow);
        Log.d("time",timeshut);
        try {
            if(check) {
                Date date1 = sdf.parse(timenow);
                Date date2 = sdf.parse(timeshut);
                long dif = date1.getTime() - date2.getTime();
                Log.d("chenhlech", dif / 1000 + "");
                count = preferencesTimeChallenge.getInt("second", 0);
                countMinute = preferencesTimeChallenge.getInt("minute", 0);
                countHour = preferencesTimeChallenge.getInt("hour", 0);
                countDay = preferencesTimeChallenge.getInt("day", 0);
                if (dif / 1000 >= 86400) {
                    countDay = countDay + (int) ((dif / 1000) / 86400);
                    int du = (int) ((dif / 1000) % 86400);
                    if (du >= 3600) {
                        countHour = countHour + du / 3600;
                        int du1 = du % 3600;
                        if (du1 >= 60) {
                            countMinute = countMinute + du1 / 60;
                            int du2 = du1 % 60;
                            count = count + du2;
                        }
                    }
                } else if (dif / 1000 >= 3600) {
                    countHour = countHour + (int) ((dif / 1000) / 3600);
                    int du1 = (int) ((dif / 1000) % 3600);

                    if (du1 >= 60) {
                        countMinute = countMinute + du1 / 60;
                        int du2 = du1 % 60;
                        count = count + du2;
                    }
                } else if (dif / 1000 >= 60) {
                    countMinute = countMinute + (int) ((dif / 1000) / 60);
                    int du2 = (int) ((dif / 1000) % 60);
                    count = count + du2;
                } else {count = count + (int) (dif / 1000);
                countMinute = 0;
                countHour = 0;
                countDay = 0;
                }
            }

            T = new Timer();
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            count++;

                            tvgiay.setText(count+"");
                            tvphut.setText(countMinute+"");
                            tvgio.setText(countHour+"");
                            tvngay.setText(countDay+"");
                            if(count>=60){
                                count = 0;
                                countMinute++;
                                tvphut.setText(countMinute+"");
                                if (countMinute >= 60){
                                    countMinute = 0;
                                    countHour++;
                                    tvgio.setText(countHour+"");
                                    if (countHour>=24){
                                        countHour = 0;
                                        countDay++;
                                        tvngay.setText(countDay+"");
                                    }
                                }
                            }




                        }
                    });
                }
            },1000,1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("trangthai","Start");

        preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
        Boolean check = preferencesTimeChallenge.getBoolean("checkhave",false);
        if (check){
            setChallenge();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("trangthai","resume");
    }

    @Override
    public void onStop() {
        super.onStop();
        preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
        Boolean check = preferencesTimeChallenge.getBoolean("checkhave",false);
        if (check) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
//
            Log.d("trangthai", "stop");
            T.cancel();
            preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
            editor.putInt("second", count);
            editor.putInt("minute", countMinute);
            editor.putInt("hour", countHour);
            editor.putInt("day", countDay);
            editor.putString("timeshut", sdf.format(new Date()));
//
            editor.commit();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("trangthai","destroyview");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("trangthai","destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
        Boolean check = preferencesTimeChallenge.getBoolean("checkhave",false);
        if(check) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
//
            Log.d("trangthai", "detach");
            T.cancel();
            preferencesTimeChallenge = getContext().getSharedPreferences("TimeChallenge", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencesTimeChallenge.edit();
            editor.putInt("second", count);
            editor.putInt("minute", countMinute);
            editor.putInt("hour", countHour);
            editor.putInt("day", countDay);
            editor.putString("timeshut", sdf.format(new Date()));
//
            editor.commit();
        }
    }
}