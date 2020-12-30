package com.wyong.myretrofit.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.wyong.myretrofit.R;
import com.wyong.myretrofit.domain.JsonResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class JsonResultListAdapter extends RecyclerView.Adapter<JsonResultListAdapter.InnerHolder> {

    private List<JsonResult.DataBean> data = new ArrayList<>();
    private static final String TAG = "JsonResultListAdapter";

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json_result, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        ImageView coverView = holder.itemView.findViewById(R.id.item_cover);
        TextView titleTv = holder.itemView.findViewById(R.id.item_title);
        TextView authorTv = holder.itemView.findViewById(R.id.item_user_name);
        TextView viewCountTv = holder.itemView.findViewById(R.id.item_view_count);

        JsonResult.DataBean dataBean = data.get(position);
        titleTv.setText(dataBean.getTitle());
        authorTv.setText(dataBean.getUserName());
        authorTv.setText(dataBean.getViewCount());

        //使用Glide加载图片
        Glide.with(holder.itemView.getContext()).load("http://192.168.137.1:9102/" + dataBean.getCover()).into(coverView);
//        Log.d(TAG,"cover url ===> " + dataBean.getCover());
        //使用Picasso 加载图片
//        官方地址：https://square.github.io/picasso/
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
//        Picasso.get().load("http://192.168.137.1:9102/" + dataBean.getCover()).into(coverView);
//        Picasso.get().load("http://192.168.137.1:9102/" + dataBean.getCover()).into(coverView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JsonResult jsonResult) {
        data.clear();
        data.addAll(jsonResult.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
