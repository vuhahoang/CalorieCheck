package com.example.caloriecheck.Activitiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caloriecheck.R;
import com.example.caloriecheck.fragment.CookFragment;
import com.example.caloriecheck.fragment.DiaryFragment;
import com.example.caloriecheck.fragment.HomeFragment;
import com.example.caloriecheck.fragment.InfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView tdee;
    double r;
    int can;
    int chieucao;
    int k;
    int tuoi;
    Double calorie;
    int nhucau;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton,fab_bs,fab_bp,fab_btr,fab_bt,fab_sd;
    FrameLayout frameLayout;
    boolean check = false;
    TextView tvbs,tvbp,tvbtr,tvbt,tvsd;
    ImageView img;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setBackground(null);
        floatingActionButton = findViewById(R.id.fab);
        fab_bp = findViewById(R.id.fab_buaphu);
        fab_bs = findViewById(R.id.fab_buasang);
        fab_btr = findViewById(R.id.fab_buatrua);
        fab_bt = findViewById(R.id.fab_buatoi);
        fab_sd = findViewById(R.id.fab_sodo);
        tvbs = findViewById(R.id.tvbuasang);
        tvbp = findViewById(R.id.tvbuaphu);
        tvbtr = findViewById(R.id.tvbuatrua);
        tvbt = findViewById(R.id.tvbuatoi);
        tvsd = findViewById(R.id.tvsodo);
        frameLayout = findViewById(R.id.framelayout);
        r = intent.getDoubleExtra("R",0);
        can = intent.getIntExtra("can",0);
        chieucao = intent.getIntExtra("chieucao",0);
        k = intent.getIntExtra("K",0);
        tuoi = intent.getIntExtra("tuoi",0);
        nhucau = intent.getIntExtra("nhucau",0);
        calorie = ((9.99*can)+(6.25*chieucao)-(4.92*tuoi)+k)*r+nhucau;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == false){
                    Hien();
                    check = true;
                }else {
                    An();
                    check = false;
                }

            }
        });







    }

    public void Hien(){
        fab_bp.show();
        fab_bs.show();
        fab_btr.show();
        fab_bt.show();
        fab_sd.show();
        tvbp.setText("Bữa phụ");
        tvbs.setText("Bữa sáng");
        tvbtr.setText("Bữa trưa");
        tvbt.setText("Bữa tối");
        tvsd.setText("Số đo");
    }
    public void An(){
        fab_bp.hide();
        fab_bs.hide();
        fab_btr.hide();
        fab_bt.hide();
        fab_sd.hide();
        tvbp.setText(null);
        tvbs.setText(null);
        tvbtr.setText(null);
        tvbt.setText(null);
        tvsd.setText(null);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
        Fragment fragment = new HomeFragment();
        if(item.getItemId() == R.id.ic_home){
           fragment = new HomeFragment();
        }else if(item.getItemId() == R.id.ic_diary){
            fragment = new DiaryFragment();
        }else if (item.getItemId() == R.id.ic_cook){
            fragment = new CookFragment();
        }else if(item.getItemId() == R.id.ic_info){
            fragment = new InfoFragment();
        }
        loadfragment(fragment);
        return true;
    }



    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
    }
}