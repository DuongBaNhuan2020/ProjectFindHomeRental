package com.example.navigationdrawerapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerapplication.MainActivity;
import com.example.navigationdrawerapplication.PaginationScrollListener;
import com.example.navigationdrawerapplication.R;
import com.example.navigationdrawerapplication.Room;
import com.example.navigationdrawerapplication.RoomHomeAdapter;
import com.example.navigationdrawerapplication.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ImageButton btnSearch, btnMenu;
    private EditText edtSearch;
    private RecyclerView recyclerView;
    private RoomHomeAdapter adapter;
    private List<Room> mListRoom;
    private MainActivity mainActivity;
    private boolean isLoading, isLastPage;
    private int currentPage=1, totalPage=2;
//    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity= (MainActivity) getActivity();
//        final TextView textView = root.findViewById(R.id.buttonhome);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
////                textView.setText(s);
//            }
//        });
        //From findRoom
        recyclerView = root.findViewById(R.id.recycler_home);
        btnSearch = root.findViewById(R.id.ImageButtonSearch);
//        btnMenu = findViewById(R.id.menu_icon);
        edtSearch = root.findViewById(R.id.EditTextSearch);
        adapter = new RoomHomeAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, 2);
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
                    Toast.makeText(mainActivity, "Bạn chưa nhập thông tin", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(mainActivity, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
        return root;
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
        Toast.makeText(mainActivity, "Load data page", Toast.LENGTH_SHORT).show();

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