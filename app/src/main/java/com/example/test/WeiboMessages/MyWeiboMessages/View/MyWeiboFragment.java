package com.example.test.WeiboMessages.MyWeiboMessages.View;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.BaseModel.Utils;
import com.example.test.WeiboMessages.AllWeiboMessages.Adapter.MessageAdapter;
import com.example.test.BaseModel.Message;
import com.example.test.WeiboMessages.FragmentsGetData.GetLists;
import com.example.test.R;

import java.util.ArrayList;

/**
 * Created by cyc20 on 2017/12/6.
 */

public class MyWeiboFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int progress;
    private Context context = null;
    public ArrayList<Message> lists = new ArrayList<>();
    //public TaskAdapter adapter;
    MessageAdapter adapter;
    private String mParam1;
    private String mParam2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            adapter.notifyDataSetChanged();

        }
    };

    public MyWeiboFragment() {

    }

    public static MyWeiboFragment newInstance(int arg) {
        MyWeiboFragment fragment = new MyWeiboFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        this.context = context;

        super.onAttach(context);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
     /*  if (lists==null){
            lists=new ArrayList<>();
        }
        if(savedInstanceState!=null){
            lists=(ArrayList)getArguments().getSerializable(ARG_PARAM1);
        }*/
        View view = inflater.inflate(R.layout.fragment_weibo, null);
        initDate();
        adapter = new MessageAdapter(lists, context);
        Log.d("adapter:", "finish");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new RecyclerViewDivider);
        Log.d("recyclerView:", "finish");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    private void initDate() {
        lists = GetLists.getList(handler, Utils.getContentUrl);

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
