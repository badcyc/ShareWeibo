package com.example.test.weibo.Comments.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.BaseModel.Utils;
import com.example.test.weibo.Comments.Adapter.ComentRepostAdapter;
import com.example.test.weibo.Comments.Model.CommentData;
import com.example.test.weibo.Comments.Presenter.GetData;
import com.example.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyc20 on 2017/12/7.
 */

public class ForwardedFragment extends Fragment {


    private String id;
    private ArrayList<CommentData> dataList=new ArrayList<>();
    @BindView(R.id.mrecyclerview) RecyclerView mRecyclerView;
    private Context context;
    private int mPage;
    public static final String MERCHANT_DETAILS_PAGE = "MERCHANT_DETAILS_PAGE";
    private ComentRepostAdapter mAdapter;
    public static ForwardedFragment newInstance(int page,String id){
        Bundle args=new Bundle();
        args.putInt(MERCHANT_DETAILS_PAGE,page);
        args.putString("id",id);
        ForwardedFragment fragment=new ForwardedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getInt(MERCHANT_DETAILS_PAGE);
        id=getArguments().getString("id");
        context=getActivity().getApplicationContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.forwardedfragment_main,container,false);
        ButterKnife.bind(this,view);
        switch (mPage){
            case 0:
                initDate();
                initAdapter(dataList);
                break;
            case 1:
                initDate();
                initAdapter(dataList);
                break;
            case 2:
                initDate();
                initAdapter(dataList);
                break;

        }
        return view;

    }
    private synchronized void initDate(){
        dataList= GetData.getCommentRepostData(Utils.getCommentsDataUrl,id);
    }
    private void initAdapter(ArrayList<CommentData> data) {
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ComentRepostAdapter(getActivity(),data);

        mRecyclerView.setAdapter(mAdapter);//设置adapter
        //设置item点击事件
    }
}
