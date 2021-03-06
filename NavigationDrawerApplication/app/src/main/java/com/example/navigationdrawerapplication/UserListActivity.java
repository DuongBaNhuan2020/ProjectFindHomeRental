package com.example.navigationdrawerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter UserAdapter;
    private List<User> mListUser;
    private ProgressBar progressBar;
    private ImageButton imgBtnBack;
    private SearchView searchView;
//    private ImageButton btnFilter, btnSort;
//    private TextView txtTotalResults;

    private boolean isLoading, isLastPage;
    private int currentPage=1, totalPage=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recycler_view_user);
        progressBar = findViewById(R.id.progress_bar);
        imgBtnBack = findViewById(R.id.ImageButtonBack);

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });
//        btnFilter = findViewById(R.id.ImgButtonFilter);
//        btnSort = findViewById(R.id.ImgButtonSort);
//        txtTotalResults = findViewById(R.id.TextViewTotalResult);
        UserAdapter = new UserAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(UserAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                progressBar.setVisibility(View.VISIBLE);
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        setFirstData();
    }

    private void loadNextPage() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> list = getListUser();
                mListUser.addAll(list);
                UserAdapter.notifyDataSetChanged();

                isLoading = false;
                progressBar.setVisibility(View.GONE);
                if(currentPage == totalPage){
                    isLastPage = true;
                }
            }
        }, 2000);
    }

    //Load data
    private void setFirstData(){
        mListUser = getListUser();
        UserAdapter.setData(mListUser);
    }
    private List<User> getListUser(){
        Toast.makeText(this, "Load data page", Toast.LENGTH_SHORT).show();

        List<User> list = new ArrayList<>();
        User u1 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u2 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u3 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u4 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u5 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u6 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u7 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u8 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u9 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);
        User u10 = new User("Tr???n Nh???t Thy", "nhatthy1224@gmail.com", R.drawable.ic_baseline_person_24);

        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
        list.add(u6);
        list.add(u7);
        list.add(u8);
        list.add(u9);
        list.add(u10);

        return list;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                UserAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                UserAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(!searchView.isIconified()){
//            searchView.setIconified(true);
//            return;
//        }
//        super.onBackPressed();
//    }
//
//    public void filterDistance(View v){
//        PopupMenu popup = new PopupMenu(this,v);
//        popup.setOnMenuItemClickListener(this);
//        popup.inflate(R.menu.distance_menu);
//        popup.show();
//    }
//    public void sortPrice(View v){
//        PopupMenu popup = new PopupMenu(this,v);
//        popup.setOnMenuItemClickListener(this);
//        popup.inflate(R.menu.price_menu);
//        popup.show();
//    }
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.item1:
//                Toast.makeText(this, "L???c theo 500m", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.item2:
//                Toast.makeText(this, "L???c theo 1000m", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.item3:
//                Toast.makeText(this, "L???c theo 1500m", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.sort1:
//                Toast.makeText(this, "S???p x???p theo gi?? t??ng d???n", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.sort2:
//                Toast.makeText(this, "S???p x???p theo gi?? gi???m d???n", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return false;
//        }
//
//    }
}