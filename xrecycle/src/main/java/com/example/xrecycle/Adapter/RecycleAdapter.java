package com.example.xrecycle.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private Context mContext;
    private List<UserBean.DataBean> mData;

    public RecycleAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_linear,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserBean.DataBean userBean = mData.get(i);
      String image = mData.get(i).getImages();
      String[] split = image.split("\\|");
      viewHolder.title_linear.setText(userBean.getTitle());
      viewHolder.price_linear.setText(userBean.getPrice()+"");
       Glide.with(mContext).load(split[0]).into(viewHolder.image_linear);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClick!=null){
                    mClick.onSuccess(userBean.getPid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setmData(List<UserBean.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addmData(List<UserBean.DataBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
    Click mClick;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_linear;
        private TextView title_linear,price_linear;
        private ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_linear = itemView.findViewById(R.id.image_linear);
            title_linear = itemView.findViewById(R.id.title_linear);
            price_linear = itemView.findViewById(R.id.price_linear);
            layout= itemView.findViewById(R.id.layout);
        }
    }
    public void setonClickListener(Click click){
        this.mClick=click;
    }


    public interface Click {
        void onSuccess(int position);
    }
}
