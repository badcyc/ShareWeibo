package com.example.test.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.Model.User.ThisUser;
import com.example.test.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cyc20 on 2017/12/4.
 */

public class AddIdAdapter extends RecyclerView.Adapter<AddIdAdapter.ViewHolder> {

    private ArrayList<ThisUser> thisUsers;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView idImageView;
        TextView id_screenName;
        TextView id_selected;
        public ViewHolder(View itemView){
            super(itemView);
            idImageView=(CircleImageView)itemView.findViewById(R.id.id_image);
            id_screenName=(TextView)itemView.findViewById(R.id.id_screen_name);
            id_selected=(TextView)itemView.findViewById(R.id.id_selected);
        }
    }
    public AddIdAdapter(ArrayList<ThisUser> arrayList,Context context){
        this.context=context;
        thisUsers=arrayList;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addid_main,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (thisUsers!=null){
            ThisUser thisUser=thisUsers.get(position);
            Glide.with(context)
                    .load(thisUser.getUserIdImageUrl())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            holder.idImageView.setImageBitmap(resource);
                        }
                    });
            holder.id_screenName.setText(thisUser.getUserIdScreenName());
            if (thisUser.isSelectedState()){
                holder.id_selected.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return thisUsers.size();
    }

}
