package com.example.caloriecheck.Activitiy;

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
import com.example.caloriecheck.R;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import dagger.hilt.android.AndroidEntryPoint;
import me.relex.circleindicator.CircleIndicator3;
@AndroidEntryPoint
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
        contentpagers.add(new ContentPager("#FF99FF","#990099","Bạn không biết cách tính calorie! Liệu mục tiêu calorie của bạn đã đúng?","",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","tự hỏi liệu mục tiêu calo của bạn có phù hợp với bạn và được tính toán chính xác hay không?","Để tính mục tiêu calorie tôi đã sử dụng Phương trình Mifflin- st jeor đó là một trong những công thức chính xác nhất. Chúng tôi cũng tính đến hoạt động và thời gian nghỉ ngơi của bạn."));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","Nếu cân nặng của bạn thay đổi trong vài tuần tôi cũng sẽ chỉnh lại calorie cho bạn cho phù hợp cân nặng hiện tại",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","Chú ý","Hãy điền đúng mục tiêu của bạn bởi vì giảm cân và tăng cơ là hai chế độ hoàn toàn khác nhau",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","Nếu bạn có được mục tiêu calorie từ bác sĩ,chuyên gia hoặc hướng dẫn viên thể hình bạn có thể thay đổi nó trong phần cài đặt",""));
        contentpagers.add(new ContentPager("#FF99FF","#990099","","Đừng quên: Mỗi người khác nhau thì mục tiêu cũng đều khác nhau!",""));
    }

    private void learntwo(){
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","Bạn nên cân đồ ăn còn sống hay là đã chín?","",""));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","đôi khi việc tính calo và trọng lượng bữa ăn của bạn không phải là điều dễ dàng","Bạn có thắc mắc rằng bạn nên cân thức ăn trước hay sau khi bạn nấu nó không?"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","Chúng tôi khuyên bạn nê cân thực phẩm của bạn trước khi bạn nấu chúng","Theo quy tắc chung, các giá trị trên bao bì của nhà sản xuất cho biết phần thực phẩm sống, chưa được nấu chín"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","Hãy lưu ý","trong một vài trường hợp, các giá trị dinh dưỡng có thể đề cập đến phần đã nấu chín tuy nhiên điều này thường được ghi trên bao bì","Trong trường hợp trên bạn có thể ghi lại trọng lượng của phần đã nấu chín"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","Bạn có biết giá trị dinh dưỡng thường đề cập đến các phần ăn được của thực phẩm (ví dụ: với thịt, trọng lượng không bao gồm xương)","Do đó không nên cân phần không ăn được bạn nhé"));
        contentpagers.add(new ContentPager("#FCBBF1","#5E004E","","Hãy mua một chiếc cân nhỏ và bắt đầu ghi lại thức ăn của bạn nào!",""));
     }

     private void learnthree(){
        contentpagers.add(new ContentPager("#E7F686","#657500","Bạn có cần phải cân tất cả mọi thứ bạn ăn hoặc uống","",""));
        contentpagers.add(new ContentPager("#E7F686","#657500","","Nó có thể tốn nhiều thời gian","Tuy nhiên để giữ nhật ký của bạn chính xác nhất có thể chúng tôi khuyên bạn nên cân tất cả các thực phẩm bằng cân nhà bếp trước khi tiêu thụ"));
        contentpagers.add(new ContentPager("#E7F686","#657500","","Sau khi sủ dụng cân nhà bếp một thời gian bạn cũng sẽ hiểu rõ hơn về trọng lượng","Tuy nhiên bạn không thể cân mọi thứ bạn cũng có thể ước lượng chúng."));
        contentpagers.add(new ContentPager("#E7F686","#657500","","Chỉ với một chút tập luyện bạn có thể sẽ trở nên chuyên nghiệp trong việc ước lượng","Chúc may mắn"));
    }

    private void learnfour(){
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","Bạn nên theo dõi bữa ăn như thế nào khi bạn ăn ở ngoài?","",""));
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","","THeo dõi lượng calo tại nhà hàng, tại một bữa tiệc nướng hoặc trong một buổi buffet là một thách thức lớn.","Trong trường hợp này bạn chỉ có thể ước tính khẩu phần và từng thành phần riêng lẻ"));
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","","Nếu bạn biết các thành phần của một món ăn,bạn có thể nhập từng thành phần vào ứng dụng",""));
        contentpagers.add(new ContentPager("#A5DCFF","#003B62","","Hãy tiếp tục ngay cả khi việc theo dõi gặp khó khăn.","Tôi ở đây để giúp đỡ bạn"));
    }

    private void learnfive(){
        contentpagers.add(new ContentPager("#CEFFC1","#115400","Nếu bạn không tập luyện thể thao bạn có thể giảm cân được không?","",""));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Hoàn toàn có thể, bạn có thể giảm cân chỉ với việc ăn uống khoa học","Tuy nhiên tập luyện sẽ giúp bạn trao đổi trất tốt hơn và có thế tiêu thu nhiều calo trong ngày hơn một chút"));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Tuy nhiên bạn hãy cố đảm bảo bạn hoàn thành mục tiêu calo trong ngày","Nếu bạn tập quá nhiều làm thâm hụt calo có thể làm chậm quá trình giảm cân của bạn"));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Miễn là bạn không vượt mục tiêu calo thì cuối cùng bạn sẽ đạt được mục tiêu giảm cân",""));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","Lưu ý: Bạn không nên hạn chế tiêu thụ những thực phẩm không tốt như đồ ngọt hoặc đồ ăn nhanh","Hãy tối ưu sử dụng thực phẩm giàu protein hoặc carbohydrate phức hợp(Vd: Khoai tây,yến mạch,gạo nguyên hạt)"));
        contentpagers.add(new ContentPager("#CEFFC1","#115400","","hãy thử và xem phương pháp nào phù hợp nhất với bạn","Chúc may mắn!"));
    }

}