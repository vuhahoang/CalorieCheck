package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.example.caloriecheck.CustomApdapter.FragmentAdapter;
import com.example.caloriecheck.Model.ContentPager;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import me.relex.circleindicator.CircleIndicator3;

public class learn1 extends AppCompatActivity {

    ViewPager2 viewPager2;
    CircleIndicator3 circleIndicator3;
    ArrayList<ContentPager> contentpagers;
    ImageView close;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int pos = viewPager2.getCurrentItem();

            if(pos == contentpagers.size()-1){
//                onBackPressed();
            }else {
                viewPager2.setCurrentItem(pos + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn1);
        viewPager2 = findViewById(R.id.viewpager2);
        circleIndicator3 = findViewById(R.id.circle_indicator);
        Intent i = getIntent();
        int check = i.getIntExtra("keylearn",0);
        close = findViewById(R.id.close);
        contentpagers = new ArrayList<>();
        switch (check){
            case 1: learnone();
            break;
            case 2: learntwo();
            break;
            case 3:learnthree();
            break;
            case 4:learnfour();
            break;
            case 5: learnfive();
            break;
            default: learnone();
            break;
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(this,contentpagers);
        viewPager2.setAdapter(fragmentAdapter);
        circleIndicator3.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,5000);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private void learnone(){
        contentpagers.add(new ContentPager("#FF99FF","#990099","B???n kh??ng bi???t c??ch t??nh calorie! Li???u m???c ti??u calorie c???a b???n ???? ????ng?","",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","t??? h???i li???u m???c ti??u calo c???a b???n c?? ph?? h???p v???i b???n v?? ???????c t??nh to??n ch??nh x??c hay kh??ng?","????? t??nh m???c ti??u calorie t??i ???? s??? d???ng Ph????ng tr??nh Mifflin- st jeor ???? l?? m???t trong nh???ng c??ng th???c ch??nh x??c nh???t. Ch??ng t??i c??ng t??nh ?????n ho???t ?????ng v?? th???i gian ngh??? ng??i c???a b???n."));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","N???u c??n n???ng c???a b???n thay ?????i trong v??i tu???n t??i c??ng s??? ch???nh l???i calorie cho b???n cho ph?? h???p c??n n???ng hi???n t???i",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","Ch?? ??","H??y ??i???n ????ng m???c ti??u c???a b???n b???i v?? gi???m c??n v?? t??ng c?? l?? hai ch??? ????? ho??n to??n kh??c nhau",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","N???u b???n c?? ???????c m???c ti??u calorie t??? b??c s??,chuy??n gia ho???c h?????ng d???n vi??n th??? h??nh b???n c?? th??? thay ?????i n?? trong ph???n c??i ?????t",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","?????ng qu??n: M???i ng?????i kh??c nhau th?? m???c ti??u c??ng ?????u kh??c nhau!",""));
    }

    private void learntwo(){
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","B???n n??n c??n ????? ??n c??n s???ng hay l?? ???? ch??n?","",""));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","????i khi vi???c t??nh calo v?? tr???ng l?????ng b???a ??n c???a b???n kh??ng ph???i l?? ??i???u d??? d??ng","B???n c?? th???c m???c r???ng b???n n??n c??n th???c ??n tr?????c hay sau khi b???n n???u n?? kh??ng?"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","Ch??ng t??i khuy??n b???n n?? c??n th???c ph???m c???a b???n tr?????c khi b???n n???u ch??ng","Theo quy t???c chung, c??c gi?? tr??? tr??n bao b?? c???a nh?? s???n xu???t cho bi???t ph???n th???c ph???m s???ng, ch??a ???????c n???u ch??n"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","H??y l??u ??","trong m???t v??i tr?????ng h???p, c??c gi?? tr??? dinh d?????ng c?? th??? ????? c???p ?????n ph???n ???? n???u ch??n tuy nhi??n ??i???u n??y th?????ng ???????c ghi tr??n bao b??","Trong tr?????ng h???p tr??n b???n c?? th??? ghi l???i tr???ng l?????ng c???a ph???n ???? n???u ch??n"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","B???n c?? bi???t gi?? tr??? dinh d?????ng th?????ng ????? c???p ?????n c??c ph???n ??n ???????c c???a th???c ph???m (v?? d???: v???i th???t, tr???ng l?????ng kh??ng bao g???m x????ng)","Do ???? kh??ng n??n c??n ph???n kh??ng ??n ???????c b???n nh??"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","H??y mua m???t chi???c c??n nh??? v?? b???t ?????u ghi l???i th???c ??n c???a b???n n??o!",""));
     }

     private void learnthree(){
        contentpagers.add(new ContentPager("#E7F686","#657500","B???n c?? c???n ph???i c??n t???t c??? m???i th??? b???n ??n ho???c u???ng","",""));
        contentpagers.add(new ContentPager("#E7F686","#657500","","N?? c?? th??? t???n nhi???u th???i gian","Tuy nhi??n ????? gi??? nh???t k?? c???a b???n ch??nh x??c nh???t c?? th??? ch??ng t??i khuy??n b???n n??n c??n t???t c??? c??c th???c ph???m b???ng c??n nh?? b???p tr?????c khi ti??u th???"));
        contentpagers.add(new ContentPager("#E7F686","#657500","","Sau khi s??? d???ng c??n nh?? b???p m???t th???i gian b???n c??ng s??? hi???u r?? h??n v??? tr???ng l?????ng","Tuy nhi??n b???n kh??ng th??? c??n m???i th??? b???n c??ng c?? th??? ?????c l?????ng ch??ng."));
        contentpagers.add(new ContentPager("#E7F686","#657500","","Ch??? v???i m???t ch??t t???p luy???n b???n c?? th??? s??? tr??? n??n chuy??n nghi???p trong vi???c ?????c l?????ng","Ch??c may m???n"));
    }

    private void learnfour(){
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","B???n n??n theo d??i b???a ??n nh?? th??? n??o khi b???n ??n ??? ngo??i?","",""));
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","","THeo d??i l?????ng calo t???i nh?? h??ng, t???i m???t b???a ti???c n?????ng ho???c trong m???t bu???i buffet l?? m???t th??ch th???c l???n.","Trong tr?????ng h???p n??y b???n ch??? c?? th??? ?????c t??nh kh???u ph???n v?? t???ng th??nh ph???n ri??ng l???"));
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","","N???u b???n bi???t c??c th??nh ph???n c???a m???t m??n ??n,b???n c?? th??? nh???p t???ng th??nh ph???n v??o ???ng d???ng",""));
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","","H??y ti???p t???c ngay c??? khi vi???c theo d??i g???p kh?? kh??n.","T??i ??? ????y ????? gi??p ????? b???n"));
    }

    private void learnfive(){
        contentpagers.add(new ContentPager("#CEFFC1","#115400","N???u b???n kh??ng t???p luy???n th??? thao b???n c?? th??? gi???m c??n ???????c kh??ng?","",""));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Ho??n to??n c?? th???, b???n c?? th??? gi???m c??n ch??? v???i vi???c ??n u???ng khoa h???c","Tuy nhi??n t???p luy???n s??? gi??p b???n trao ?????i tr???t t???t h??n v?? c?? th??? ti??u thu nhi???u calo trong ng??y h??n m???t ch??t"));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Tuy nhi??n b???n h??y c??? ?????m b???o b???n ho??n th??nh m???c ti??u calo trong ng??y","N???u b???n t???p qu?? nhi???u l??m th??m h???t calo c?? th??? l??m ch???m qu?? tr??nh gi???m c??n c???a b???n"));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Mi???n l?? b???n kh??ng v?????t m???c ti??u calo th?? cu???i c??ng b???n s??? ?????t ???????c m???c ti??u gi???m c??n",""));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","L??u ??: B???n kh??ng n??n h???n ch??? ti??u th??? nh???ng th???c ph???m kh??ng t???t nh?? ????? ng???t ho???c ????? ??n nhanh","H??y t???i ??u s??? d???ng th???c ph???m gi??u protein ho???c carbohydrate ph???c h???p(Vd: Khoai t??y,y???n m???ch,g???o nguy??n h???t)"));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","h??y th??? v?? xem ph????ng ph??p n??o ph?? h???p nh???t v???i b???n","Ch??c may m???n!"));
    }

}