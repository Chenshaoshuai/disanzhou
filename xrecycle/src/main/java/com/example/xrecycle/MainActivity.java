package com.example.xrecycle;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xrecycle.Adapter.RecycleAdapter;
import com.example.xrecycle.Adapter.RecycleGridAdapter;
import com.example.xrecycle.Bean.UserBean;
import com.example.xrecycle.P.IPersenterImpl;
import com.example.xrecycle.V.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {
   private XRecyclerView  xRecyclerView;
   private IPersenterImpl persenter;
   private Button btn_toggle;
   private TextView tv_synthesize,tv_sales,tv_price;
   private List<UserBean.DataBean> data = new ArrayList<>();
   private RecycleAdapter adapter;
   private RecycleGridAdapter gridAdapter;
   private int mPage;
   private boolean isLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xRecyclerView = findViewById(R.id.recyclerview);
        tv_price = findViewById(R.id.tv_price);
        tv_sales = findViewById(R.id.tv_sales);
        tv_synthesize = findViewById(R.id.tv_synthesize);
        tv_synthesize.setOnClickListener(this);
        tv_sales.setOnClickListener(this);
        tv_price.setOnClickListener(this);
        btn_toggle = findViewById(R.id.btn_toggle);
        btn_toggle.setOnClickListener(this);
        persenter = new IPersenterImpl(this);
        isLinear = true;
        mPage =1;

        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                mPage++;
                loadData();

            }
        });
        setonButton();
        loadData();
        this.adapter.setonClickListener(
                new RecycleAdapter.Click() {
                    @Override
                    public void onSuccess(int position) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("name",position+"");
                        startActivity(intent);
                    }
                }
        );
    }

    private void loadData() {
        Map<String,String> params = new HashMap<>();
        params.put("keywords","手机");
        params.put("page",mPage+"");
        persenter.getRequest(Apis.TYPE_DATA,params,UserBean.class);
    }
   public void setonButton(){
        if(isLinear){
            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            xRecyclerView.setLayoutManager(manager);
            adapter = new RecycleAdapter(this);
            xRecyclerView.setAdapter(adapter);
        }else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
            xRecyclerView.setLayoutManager(gridLayoutManager);
            gridAdapter = new RecycleGridAdapter(this);
            xRecyclerView.setAdapter(gridAdapter);
        }
        isLinear = !isLinear;
   }


    @Override
    public void onSuccess(Object data) {
         if(data instanceof UserBean){
            UserBean userBean = (UserBean) data;
            if(mPage ==1){
                if(!isLinear){
                  adapter.setmData(userBean.getData());
                }else{
                    gridAdapter.setmData(userBean.getData());
                }
            }else {
                if(!isLinear){
                     adapter.addmData(userBean.getData());
                }else {
                    gridAdapter.addmData(userBean.getData());
                }
            }
         }
         xRecyclerView.loadMoreComplete();
         xRecyclerView.refreshComplete();
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.btn_toggle:
             setonButton();
             loadData();
             break;
         case R.id.tv_price:
             mPage=1;
             tv_price.setTextColor(Color.RED);
             tv_sales.setTextColor(Color.GRAY);
             tv_synthesize.setTextColor(Color.GRAY);
             Map<String,String> params1=new HashMap<>();
             params1.put("sort",0+"");
             params1.put("keywords","手机");
             params1.put("page",mPage+"");
             persenter.getRequest(Apis.TYPE_DATA,params1,UserBean.class);
             break;
         case R.id.tv_sales:
             mPage=1;
             tv_sales.setTextColor(Color.RED);
             tv_price.setTextColor(Color.GRAY);
             tv_synthesize.setTextColor(Color.GRAY);
             Map<String,String> params2=new HashMap<>();
             params2.put("sort",1+"");
             params2.put("keywords","手机");
             params2.put("page",mPage+"");
             persenter.getRequest(Apis.TYPE_DATA,params2,UserBean.class);
             break;
         case R.id.tv_synthesize:
             mPage=1;
             tv_synthesize.setTextColor(Color.RED);
             tv_price.setTextColor(Color.GRAY);
             tv_sales.setTextColor(Color.GRAY);
             Map<String,String> params3=new HashMap<>();
             params3.put("sort",2+"");
             params3.put("keywords","手机");
             params3.put("page",mPage+"");
             persenter.getRequest(Apis.TYPE_DATA,params3,UserBean.class);
             break;
             default:
                 break;
     }
    }
}
