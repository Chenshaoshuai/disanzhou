package com.example.xrecycle.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xrecycle.Bean.UserBean;
import com.example.xrecycle.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecycleGridAdapter extends RecyclerView.Adapter<RecycleGridAdapter.ViewHolder> {
   private Context mContext;
   private List<UserBean.DataBean> mData;

    public RecycleGridAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_grid,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    UserBean.DataBean userBean = mData.get(i);
        String image = mData.get(i).getImages();
        String[] split = image.split("\\|");
        viewHolder.title_grid.setText(userBean.getTitle());
        viewHolder.price_grid.setText(userBean.getPrice()+"");
        Glide.with(mContext).load(split[0]).into(viewHolder.image_grid);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setmData(List<UserBean.DataBean> mDatas) {
        mData.clear();
        mData.addAll(mDatas);
        notifyDataSetChanged();
    }

    public List<UserBean.DataBean> getmData() {
        return mData;
    }

    public void addmData(List<UserBean.DataBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title_grid,price_grid;
        private ImageView image_grid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_grid = itemView.findViewById(R.id.title_grid);
            price_grid = itemView.findViewById(R.id.price_grid);
            image_grid = itemView.findViewById(R.id.image_grid);
        }
    }
}
