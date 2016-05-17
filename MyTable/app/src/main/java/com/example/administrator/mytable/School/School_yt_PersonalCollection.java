package com.example.administrator.mytable.School;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.adapter.CollectionBaseAdapter;
import com.example.administrator.mytable.School.db.DBManager;

import java.util.ArrayList;
import java.util.List;
//
public class School_yt_PersonalCollection extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private LinearLayout iBack;
    private ListView lvList;
    private CollectionBaseAdapter adapter;
    private List<Theme> collectEntityList;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__to_see__personal_collection);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        lvList= (ListView) super.findViewById(R.id.lvList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbManager=new DBManager(this);
        initListView();
        initViewLisener();
    }

    private void initListView() {
        collectEntityList=new ArrayList<Theme>();
        loadDBSqlite();///////////////
        adapter=new CollectionBaseAdapter(this,collectEntityList);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(this);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private void loadDBSqlite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Theme>  collectEntityLists=dbManager.searchCollectEntity();
                collectEntityList.addAll(collectEntityLists);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initViewLisener() {
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Theme collectEntity=collectEntityList.get(position);
        Intent intent=new Intent(this,WebView_News_Detil_Activity2.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",collectEntity);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
