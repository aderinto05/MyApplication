package com.myapplication.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.ui.main.MainActivity;
import com.myapplication.ui.main.adapter.ItemAdapter;
import com.myapplication.model.Item;
import com.myapplication.model.ItemChild;
import com.myapplication.util.LinearManager;
import com.myapplication.util.application.Application;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements ItemAdapter.OnItemClickList{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    Unbinder unbinder;
    private View rootView;
    private LinearManager mLinearLayoutManager;

    List<Item> itemList = new ArrayList<Item>();

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mLinearLayoutManager = new LinearManager(Application.getAppContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        loadData();
        return rootView;
    }

    private void loadData(){
        if(itemList.size() == 0){
            ItemChild itemChild1 = new ItemChild("rak.jpeg");
            ItemChild itemChild2 = new ItemChild("westafel.jpeg");
            ItemChild itemChild3 = new ItemChild("friend.jpeg");
            ItemChild itemChild4 = new ItemChild("image1.jpg");
            List<ItemChild> itemChildListA = new ArrayList<ItemChild>();
            itemChildListA.add(itemChild1);
            itemChildListA.add(itemChild2);
            itemChildListA.add(itemChild3);
            Item itemA = new Item("rak.jpeg", itemChildListA);


            List<ItemChild> itemChildListB = new ArrayList<ItemChild>();
            itemChildListB.add(itemChild3);
            itemChildListB.add(itemChild2);
            itemChildListB.add(itemChild1);
            itemChildListB.add(itemChild4);
            Item itemB = new Item("friend.jpeg", itemChildListB);

            List<ItemChild> itemChildListC = new ArrayList<ItemChild>();
            itemChildListC.add(itemChild2);
            itemChildListC.add(itemChild1);
            itemChildListC.add(itemChild3);
            Item itemC = new Item("westafel.jpeg", itemChildListC);

            itemList.add(itemA);
            itemList.add(itemB);
            itemList.add(itemC);
            itemList.add(itemB);
            itemList.add(itemA);
            itemList.add(itemC);
        }

        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), itemList);
        itemAdapter.setOnItemClickList(this);
        itemAdapter.setHasStableIds(true);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onClickItemList(int position) {
        ((MainActivity) getActivity()).showDetailItem(itemList.get(position).getItemChild());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

}
