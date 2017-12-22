package com.example.test.weibo.Comments.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.BaseModel.Utils;
import com.example.test.base.ui.SupportFragment;
import com.example.test.weibo.Comments.Adapter.ComentRepostAdapter;
import com.example.test.weibo.Comments.Bean.CommentData;
import com.example.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.test.weibo.Comments.Bean.Utils.getCommentRepostData;

/**
 * Created by cyc20 on 2017/12/7.
 */

public class ForwardedFragment extends SupportFragment implements CommentsView{
    private String id;
    private ArrayList<CommentData> dataList = new ArrayList<>();
    @BindView(R.id.mrecyclerview)
    RecyclerView mRecyclerView;
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
        // context=getActivity().getApplicationContext();
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        context=getActivity();
        View view=inflater.inflate(R.layout.forwardedfragment_main,container,false);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        dataList = getCommentRepostData(Utils.getCommentsDataUrl, id);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        ButterKnife.bind(this,parentView);
        //mRecyclerView=(RecyclerView)parentView.findViewById(R.id.mrecyclerview);
        switch (mPage) {
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
    }
    private synchronized void initDate(){
        dataList= getCommentRepostData(Utils.getCommentsDataUrl,id);

    }
    private void initAdapter(ArrayList<CommentData> data) {
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new ComentRepostAdapter(context,data);

        mRecyclerView.setAdapter(mAdapter);//设置adapter
        //设置item点击事件
    }

    @Override
    public void success(CommentData model) {

    }

    @Override
    public void error(int code, String msg) {

    }
}
