//package com.example.caloriecheck.Repository.Imp;
//
//import com.example.caloriecheck.Model.Dayrecipe;
//import com.example.caloriecheck.Model.Plan;
//import com.example.caloriecheck.Model.StaticListModel;
//import com.example.caloriecheck.Repository.ILearn2Repository;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//public class Learn2RepositoryImp implements ILearn2Repository {
//    private final FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;
//
//    @Inject
//    public Learn2RepositoryImp(FirebaseDatabase firebaseDatabase) {
//        this.firebaseDatabase = firebaseDatabase;
//        this.databaseReference = firebaseDatabase.getReference("Plan");
//    }
//
//    @Override
//    public Task<Void> setPlans(ArrayList<Plan> plans) {
//        this.databaseReference.setValue(plans);
//        return null;
//    }
//
//    @Override
//    public void initData() {
//
//    }
////        firebaseRecipe = FirebaseDatabase.getInstance();
////        databaseRecipe = firebaseRecipe.getReference("Plan");
//
//    private ArrayList<Dayrecipe> getDayRecipe() {
//        ArrayList<Dayrecipe> dayrecipes = new ArrayList<>();
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/716429-556x370.jpg", "Ngày 1", list1));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg", "Ngày 2", list2));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/76424-556x370.jpeg", "Ngày 3", list3));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/792072-556x370.jpg", "Ngày 4", list4));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/111571-556x370.jpg", "Ngày 5", list5));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg", "Ngày 6", list6));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg", "Ngày 7", list7));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/716429-556x370.jpg", "Ngày 8", list8));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg", "Ngày 9", list9));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/867882-556x370.jpg", "Ngày 10", list10));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206755-556x370.jpg", "Ngày 11", list11));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/658967-556x370.jpg", "Ngày 12", list12));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/636601-556x370.jpg", "Ngày 13", list13));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/867882-556x370.jpg", "Ngày 14", list14));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg", "Ngày 15", list15));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg", "Ngày 16", list16));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg", "Ngày 17", list17));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/716429-556x370.jpg", "Ngày 18", list18));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg", "Ngày 19", list19));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg", "Ngày 20", list20));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg", "Ngày 21", list21));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg", "Ngày 22", list22));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/792072-556x370.jpg", "Ngày 23", list23));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg", "Ngày 24", list24));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/111571-556x370.jpg", "Ngày 25", list25));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/658967-556x370.jpg", "Ngày 26", list26));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/792072-556x370.jpg", "Ngày 27", list27));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg", "Ngày 28", list28));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg", "Ngày 29", list29));
//        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/76424-556x370.jpg", "Ngày 30", list30));
//        return dayrecipes;
//    }
//
//    private ArrayList<Plan> getPlan() {
//        ArrayList<Plan> plans = new ArrayList<>();
//        plans.add(new Plan("Giảm cân trong 30 ngày", "Không hạn chế ăn uống", "https://www.englishclub.com/images/vocabulary/food/health/healthy.jpg", dayrecipes));
//        plans.add(new Plan("28 Ngày thử thách thon gọn", "Không hạn chế ăn uống", "https://dizzybusyandhungry.com/wp-content/uploads/2018/12/healthier-spaghetti-2.jpg", dayrecipes));
//        return plans;
//    }
//
//    private Map<String, ArrayList<String>> getCodeList(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3, ArrayList<String> list4, ArrayList<String> list5, ArrayList<String> list6, ArrayList<String> list7, ArrayList<String> list8, ArrayList<String> list9, ArrayList<String> list10, ArrayList<String> list11, ArrayList<String> list12, ArrayList<String> list13, ArrayList<String> list14, ArrayList<String> list15, ArrayList<String> list16, ArrayList<String> list17, ArrayList<String> list18, ArrayList<String> list19, ArrayList<String> list20, ArrayList<String> list21, ArrayList<String> list22, ArrayList<String> list23, ArrayList<String> list24, ArrayList<String> list25, ArrayList<String> list26, ArrayList<String> list27, ArrayList<String> list28, ArrayList<String> list29, ArrayList<String> list30) {
//        Map<String,ArrayList<String>> allList = new HashMap<>();
//        for(int i = 1;i<=30;i++) {
//            allList.put("list" + i, new ArrayList<>());
//        }
//
//        allList.get("list1").add("716429");
//        allList.get("list1").add("40565");
//        allList.get("list1").add("654935");
//        allList.get("list1").add("665303");
//
//        allList.get("list2").add("35893");
//        allList.get("list1").add("636178");
//        allList.get("list2").add("634496");
//        allList.get("list2").add("639644");
//
//        allList.get("list3").add("76424");
//        allList.get("list3").add("35893");
//        allList.get("list3").add("654935");
//        allList.get("list3").add("607524");
//
//        list4.add("792072");
//        list4.add("867882");
//        list4.add("634496");
//
//        list5.add("111571");
//        list5.add("418561");
//        list5.add("486540");
//
//        list6.add("556926");
//        list6.add("40565");
//        list6.add("658967");
//        list6.add("665303");
//
//        list7.add("206280");
//        list7.add("206755");
//        list7.add("654935");
//
//        list8.add("716429");
//        list8.add("40565");
//        list8.add("654935");
//
//        list9.add("35893");
//        list9.add("40565");
//        list9.add("36199");
//
//        list10.add("867882");
//        list10.add("497714");
//        list10.add("637624");
//        list10.add("632091");
//
//        list11.add("206755");
//        list11.add("636601");
//        list11.add("35893");
//
//        list12.add("658967");
//        list12.add("318375");
//        list12.add("654935");
//
//        list13.add("636601");
//        list13.add("40565");
//        list13.add("636178");
//        list13.add("665303");
//
//        list14.add("867882");
//        list14.add("497714");
//        list14.add("637624");
//        list14.add("632091");
//
//        list15.add("206280");
//        list15.add("206755");
//        list15.add("654935");
//
//        list16.add("556926");
//        list16.add("40565");
//        list16.add("658967");
//        list16.add("665303");
//
//        list17.add("35893");
//        list17.add("636178");
//        list17.add("634496");
//        list17.add("639644");
//
//        list18.add("716429");
//        list18.add("40565");
//        list18.add("654935");
//        list18.add("665303");
//
//        list19.add("35893");
//        list19.add("40565");
//        list19.add("36199");
//
//        list20.add("35893");
//        list20.add("636178");
//        list20.add("634496");
//        list20.add("639644");
//
//        list21.add("206280");
//        list21.add("206755");
//        list21.add("654935");
//
//        list22.add("556926");
//        list22.add("40565");
//        list22.add("658967");
//        list22.add("665303");
//
//        list23.add("792072");
//        list23.add("867882");
//        list23.add("634496");
//
//        list24.add("556926");
//        list24.add("40565");
//        list24.add("658967");
//        list24.add("665303");
//
//        allList.get("list25").add("111571");
//        allList.get("list25").add("418561");
//        allList.get("list25").add("486540");
//
//        allList.get("list26").add("658967");
//        allList.get("list26").add("318375");
//        allList.get("list26").add("654935");
//
//        allList.get("list27").add("792072");
//        allList.get("list27").add("867882");
//        allList.get("list27").add("634496");
//
//        allList.get("list28").add("206280");
//        allList.get("list28").add("206755");
//        allList.get("list28").add("654935");
//
//        allList.get("list29").add("556926");
//        allList.get("list29").add("40565");
//        allList.get("list29").add("658967");
//        allList.get("list29").add("665303");
//
//        allList.get("list30").add("76424");
//        allList.get("list30").add("35893");
//        allList.get("list30").add("654935");
//        allList.get("list30").add("607524");
//        return allList;
//    }
//}
//
