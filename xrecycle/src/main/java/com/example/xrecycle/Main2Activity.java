package com.example.xrecycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xrecycle.Bean.ToggleBean;
import com.example.xrecycle.P.IPersenterImpl;
import com.example.xrecycle.V.IView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView {
    private IPersenterImpl iPersenter;
    private Banner banner;
    private TextView title, price;
    private String name;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iPersenter = new IPersenterImpl(this);
        title = findViewById(R.id.title_title);
        price = findViewById(R.id.title_price);
        list = new ArrayList<>();
        banner = findViewById(R.id.banner);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(Main2Activity.this).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        loadData();

    }

    private void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("pid", name);
        iPersenter.getRequest(Apis.TAG_DATA, params, ToggleBean.class);
    }


    @Override
    public void onSuccess(Object data) {
        if (data instanceof ToggleBean) {
            ToggleBean toggleBean = (ToggleBean) data;
            String[] split = toggleBean.getData().getImages().split("\\|");
            for (int i = 0; i < split.length; i++) {
                list.add(split[i]);
            }
            banner.setImages(list);
            banner.start();
            title.setText(toggleBean.getData().getTitle());
            price.setText(toggleBean.getData().getPrice() + "");
        }
    }
}