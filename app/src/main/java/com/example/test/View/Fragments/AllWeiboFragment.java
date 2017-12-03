package com.example.test.View.Fragments;

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

import com.example.test.Adapters.MessageAdapter;
import com.example.test.Model.Message;
import com.example.test.Model.User;
import com.example.test.Model.Utils;
import com.example.test.Presenter.FragmentsGetData.GetLists;
import com.example.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cyc20 on 2017/11/29.
 */

public class AllWeiboFragment extends Fragment {
    private static final String ARG_PARAM1="param1";
    private int progress;
    private Context context=null;
    public ArrayList<Message>lists=new ArrayList<>();
    //public TaskAdapter adapter;
    MessageAdapter adapter;
    private String mParam1;
    private String mParam2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            adapter.notifyDataSetChanged();

        }
    };

    public AllWeiboFragment(){

    }
    public static AllWeiboFragment newInstance(ArrayList<Message> lists) {
        AllWeiboFragment fragment = new AllWeiboFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,lists);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        this.context=context;

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
        View view=inflater.inflate(R.layout.fragment_weibo,null);
        initDate();
        adapter=new MessageAdapter(lists,context);
        Log.d("adapter:","finish");
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new RecyclerViewDivider);
        Log.d("recyclerView:","finish");

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(inflater.getContext(),LinearLayoutManager.VERTICAL,false);
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
        lists=GetLists.getList(handler);

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
