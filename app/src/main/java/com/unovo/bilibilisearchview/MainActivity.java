package com.unovo.bilibilisearchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private LinearLayout mContentPanel;
    private CardView mCardViewSearch;
    private LinearLayout mSearchLayout;
    private ImageView mIvSearchBack;
    private EditText mEtSearch;
    private ImageView mClearSearch;
    private RecyclerView mRecycleview;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initView();
        initResultItem();
        initToolbar();
        initListener();
    }

    private void initResultItem() {
        ArrayList<String> list = new ArrayList<>();
        list.add("优酷");
        list.add("土豆");
        list.add("爱奇艺");
        list.add("哔哩哔哩");
        list.add("youtube");
        list.add("斗鱼");
        list.add("熊猫");
        RvAdapter adapter = new RvAdapter(list, new RvAdapter.IListener() {
            @Override
            public void normalItemClick(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clearItemClick() {
                Toast.makeText(MainActivity.this, "清除历史记录", Toast.LENGTH_SHORT).show();
            }
        });
        mRecycleview.setAdapter(adapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItem = item.getItemId();
                switch (menuItem) {
                    case R.id.action_search:
                        SearchViewUtils.handleToolBar(getApplicationContext(),mCardViewSearch,mEtSearch);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initListener() {
       mIvSearchBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SearchViewUtils.handleToolBar(getApplicationContext(),mCardViewSearch,mEtSearch);
           }
       });
    }

    private void initView() {

    }

    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mContentPanel = (LinearLayout) findViewById(R.id.contentPanel);
        mCardViewSearch = (CardView) findViewById(R.id.cardView_search);
        mSearchLayout = (LinearLayout) findViewById(R.id.search_layout);
        mIvSearchBack = (ImageView) findViewById(R.id.iv_search_back);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mClearSearch = (ImageView) findViewById(R.id.clearSearch);
        mRecycleview = (RecyclerView) findViewById(R.id.recycleview);
    }
}
