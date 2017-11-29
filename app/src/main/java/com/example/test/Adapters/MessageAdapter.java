package com.example.test.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.Model.Message;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc20 on 2017/11/29.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList messageArrayList=new ArrayList();
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_tv;
        ImageView pic_iv;
        TextView time_tv;
        TextView from_tv;
        TextView content_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            name_tv=(TextView)itemView.findViewById(R.id.name_tv);
            pic_iv=(ImageView)itemView.findViewById(R.id.pic_iv);
            time_tv=(TextView)itemView.findViewById(R.id.time_tv);
            from_tv=(TextView)itemView.findViewById(R.id.from_tv);
            content_tv=(TextView)itemView.findViewById(R.id.content_tv);
        }
    }
    public MessageAdapter(ArrayList<Message>messageArrayList, Context context){
        this.messageArrayList=messageArrayList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       Message message=(Message) messageArrayList.get(position);
       holder.content_tv.setText(message.getText());
       holder.from_tv.setText(message.getSource_url());
       holder.time_tv.setText(message.getCreated_at());
       holder.pic_iv.setImageURI(Uri.parse(message.getPic_urls()));
       holder.name_tv.setText(message.getUser().getName());
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }
}
