package com.example.navigationdrawerapplication;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener {
    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private List<Room> mListlist;
    private ProgressBar progressBar;
    private SearchView searchView;
    private ImageButton btnFilter, btnSort;
    private TextView txtTotalResults;

    private boolean isLoading, isLastPage;
    private int currentPage=1, totalPage=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recycler_view_room);
        progressBar = findViewById(R.id.progress_bar);
        btnFilter = findViewById(R.id.ImgButtonFilter);
        btnSort = findViewById(R.id.ImgButtonSort);
        txtTotalResults = findViewById(R.id.TextViewTotalResult);
        roomAdapter = new RoomAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(roomAdapter);

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
                List<Room> list = getListRoom();
                mListlist.addAll(list);
                roomAdapter.notifyDataSetChanged();

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
        mListlist = getListRoom();
        roomAdapter.setData(mListlist);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                roomAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                roomAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    public void filterDistance(View v){
        androidx.appcompat.widget.PopupMenu popup = new androidx.appcompat.widget.PopupMenu(this,v);
        popup.setOnMenuItemClickListener(SearchActivity.this);
        popup.inflate(R.menu.distance_menu);
        popup.show();
    }
    public void sortPrice(View v){
        androidx.appcompat.widget.PopupMenu popup = new androidx.appcompat.widget.PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.price_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "L???c theo 500m", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "L???c theo 1000m", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "L???c theo 1500m", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sort1:
                Toast.makeText(this, "S???p x???p theo gi?? t??ng d???n", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sort2:
                Toast.makeText(this, "S???p x???p theo gi?? gi???m d???n", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}