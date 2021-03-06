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
                    Toast.makeText(MainActivity.this, "B???n ch??a nh???p th??ng tin", Toast.LENGTH_LONG).show();
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
        Room r1 = new Room("Nh?? tr??? 1", "1,0 tri???u VND/c??n", "8 T??n H??a ????ng, Qu???n 6", R.drawable.tro1);
        Room r2 = new Room("Nh?? tr??? 2", "1,1 tri???u VND/c??n", "18 T??n H??a ????ng, Qu???n 6", R.drawable.tro2);
        Room r3 = new Room("Nh?? tr??? 3", "1,2 tri???u VND/c??n", "28 T??n H??a ????ng, Qu???n 6", R.drawable.tro3);
        Room r4 = new Room("Nh?? tr??? 4", "1,3 tri???u VND/c??n", "38 T??n H??a ????ng, Qu???n 6", R.drawable.tro4);
        Room r5 = new Room("Nh?? tr??? 5", "1,4 tri???u VND/c??n", "48 T??n H??a ????ng, Qu???n 6", R.drawable.tro5);
        Room r6 = new Room("Nh?? tr??? 6", "1,5 tri???u VND/c??n", "58 T??n H??a ????ng, Qu???n 6", R.drawable.tro6);
        Room r7 = new Room("Nh?? tr??? 7", "1,6 tri???u VND/c??n", "68 T??n H??a ????ng, Qu???n 6", R.drawable.tro7);
        Room r8 = new Room("Nh?? tr??? 8", "1,7 tri???u VND/c??n", "78 T??n H??a ????ng, Qu???n 6", R.drawable.tro8);
        Room r9 = new Room("Nh?? tr??? 9", "1,8 tri???u VND/c??n", "88 T??n H??a ????ng, Qu???n 6", R.drawable.tro1);
        Room r10 = new Room("Nh?? tr??? 10", "1,9 tri???u VND/c??n", "98 T??n H??a ????ng, Qu???n 6", R.drawable.tro2);

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