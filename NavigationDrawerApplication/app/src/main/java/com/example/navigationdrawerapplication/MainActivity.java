package com.example.navigationdrawerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //From findRoom
    private ImageButton btnSearch, btnMenu;
    private EditText edtSearch;
    private RecyclerView recyclerView;
    private RoomHomeAdapter adapter;
    private List<Room> mListRoom;

    private boolean isLoading, isLastPage;
    private int currentPage=1, totalPage=2;
    //From findRoom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //From findRoom
        recyclerView = findViewById(R.id.recycler_home);
        btnSearch = findViewById(R.id.ImageButtonSearch);
//        btnMenu = findViewById(R.id.menu_icon);
        edtSearch = findViewById(R.id.EditTextSearch);
        adapter = new RoomHomeAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
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
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtSearch.getText().toString())){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập thông tin", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
//        From findRoom
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //From findRoom
    private void loadNextPage() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Room> list = getListRoom();
                mListRoom.addAll(list);
                adapter.notifyDataSetChanged();

                isLoading = false;
                if(currentPage == totalPage){
                    isLastPage = true;
                }
            }
        }, 2000);
    }


    //Load data
    private void setFirstData(){
        mListRoom = getListRoom();
        adapter.setData(mListRoom);
    }
    private List<Room> getListRoom(){
        Toast.makeText(this, "Load data page", Toast.LENGTH_SHORT).show();

        List<Room> list = new ArrayList<>();
        Room r1 = new Room("Nhà trọ 1", "1,0 triệu VND/căn", "8 Tân Hòa Đông, Quận 6", R.drawable.tro1);
        Room r2 = new Room("Nhà trọ 2", "1,1 triệu VND/căn", "18 Tân Hòa Đông, Quận 6", R.drawable.tro2);
        Room r3 = new Room("Nhà trọ 3", "1,2 triệu VND/căn", "28 Tân Hòa Đông, Quận 6", R.drawable.tro3);
        Room r4 = new Room("Nhà trọ 4", "1,3 triệu VND/căn", "38 Tân Hòa Đông, Quận 6", R.drawable.tro4);
        Room r5 = new Room("Nhà trọ 5", "1,4 triệu VND/căn", "48 Tân Hòa Đông, Quận 6", R.drawable.tro5);
        Room r6 = new Room("Nhà trọ 6", "1,5 triệu VND/căn", "58 Tân Hòa Đông, Quận 6", R.drawable.tro6);
        Room r7 = new Room("Nhà trọ 7", "1,6 triệu VND/căn", "68 Tân Hòa Đông, Quận 6", R.drawable.tro7);
        Room r8 = new Room("Nhà trọ 8", "1,7 triệu VND/căn", "78 Tân Hòa Đông, Quận 6", R.drawable.tro8);
        Room r9 = new Room("Nhà trọ 9", "1,8 triệu VND/căn", "88 Tân Hòa Đông, Quận 6", R.drawable.tro1);
        Room r10 = new Room("Nhà trọ 10", "1,9 triệu VND/căn", "98 Tân Hòa Đông, Quận 6", R.drawable.tro2);

        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
        list.add(r7);
        list.add(r8);
        list.add(r9);
        list.add(r10);

        return list;
    }
    //From findRoom
}