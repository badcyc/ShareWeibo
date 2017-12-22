package com.example.test.weibo.weibomessages.PrivateWeiboMessages.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.weibo.weibomessages.messagebean.Message;
import com.example.test.R;
import com.example.test.weibo.BaseAdapter.MessageAdapter;
import com.example.test.weibo.Utils.GetLists;

import java.util.ArrayList;

import static com.example.test.weibo.weibomessages.PrivateWeiboMessages.config.getSelfContent;

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

    @Override
    public void onAttach(Context context) {
        this.context = context;

        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        if (savedInstanceState!=null){
            lists=savedInstanceState.getParcelableArrayList("lists");
        }else {
            initDate();
        }
        View view = inflater.inflate(R.layout.fragment_weibo, null);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    public MyWeiboFragment() {

    }

    public static MyWeiboFragment newInstance(int arg) {
        MyWeiboFragment fragment = new MyWeiboFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, arg);
        fragment.setArguments(args);
        return fragment;
    }


    private void initDate() {
            lists = GetLists.getList(handler, getSelfContent);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("lists",lists);
    }
}
