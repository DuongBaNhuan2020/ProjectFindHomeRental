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
                    Toast.makeText(mainActivity, "B???n ch??a nh???p th??ng tin", Toast.LENGTH_LONG).show();
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