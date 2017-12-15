package com.example.test.weibo.emoji;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyc20 on 2017/12/14.
 */

public class EmojiPagerFragment extends Fragment {

    private String id;
    //private ArrayList<CommentData> dataList=new ArrayList<>();

    @BindView(R.id.emoji_grid_view)
    GridView emojiGridView;
    private Context context;
    private int mPage;
    public static final String MERCHANT_DETAILS_PAGE = "MERCHANT_DETAILS_PAGE";

    //private ComentRepostAdapter mAdapter;
    public static EmojiPagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(MERCHANT_DETAILS_PAGE, page);
        // args.putString("id",id);
        EmojiPagerFragment fragment = new EmojiPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(MERCHANT_DETAILS_PAGE);
        // id=getArguments().getString("id");
        context = getActivity().getApplicationContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.emoji_gridview_main, container, false);
        ButterKnife.bind(this, view);
        switch (mPage) {
            case 0:
                initAdapter(1);
                break;
            case 1:
                initAdapter(2);
                break;
            case 2:
                initAdapter(3);
                break;
            case 3:
                initAdapter(4);
                break;
            default:
                break;
        }
        return view;

    }

    /*private synchronized void initDate(){
        dataList= getCommentRepostData(Utils.getCommentsDataUrl,id);
    }*/
  /*  private void initAdapter(ArrayList<CommentData> data) {
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ComentRepostAdapter(getActivity(),data);

        mRecyclerView.setAdapter(mAdapter);//设置adapter
        //设置item点击事件
    }*/
    private void initAdapter(int page) {
        final EmojiFaceAdapter emojiFaceAdapter = new EmojiFaceAdapter(context, page);
        emojiGridView.setAdapter(emojiFaceAdapter);
        emojiGridView.setNumColumns(7);
        emojiGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                SpannableString spannableString = new SpannableString(view
                        .getTag().toString());
                Drawable drawable = getResources().getDrawable(
                        (int) emojiFaceAdapter.getItemId(position));
                drawable.setBounds(0, 0, 35, 35);
                ImageSpan imageSpan = new ImageSpan(drawable,
                        ImageSpan.ALIGN_BOTTOM);
                spannableString.setSpan(imageSpan, 0, view.getTag().toString()
                        .length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        });
    }
}
