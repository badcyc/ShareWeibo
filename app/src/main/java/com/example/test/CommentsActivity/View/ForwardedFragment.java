package com.example.test.CommentsActivity.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.CommentsActivity.Adapter.ComentRepostAdapter;
import com.example.test.CommentsActivity.Adapter.CommentsTabPagerAdapter;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc20 on 2017/12/7.
 */

public class ForwardedFragment extends Fragment {


    RecyclerView mRecyclerView;
    private Context context;
    private int mPage;
    public static final String MERCHANT_DETAILS_PAGE = "MERCHANT_DETAILS_PAGE";
    private ComentRepostAdapter mAdapter;
    public static ForwardedFragment newInstance(int page){
        Bundle args=new Bundle();
        args.putInt(MERCHANT_DETAILS_PAGE,page);
        ForwardedFragment fragment=new ForwardedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getInt(MERCHANT_DETAILS_PAGE);
        context=getActivity().getApplicationContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.comment_activity_main,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.mrecyclerview);
        switch (mPage){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;

        }
        return view;

    }
    private void initAdapter(ArrayList<String> data) {
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ComentRepostAdapter(getActivity(),data);

        mRecyclerView.setAdapter(mAdapter);//设置adapter
        //设置item点击事件
    }
}
