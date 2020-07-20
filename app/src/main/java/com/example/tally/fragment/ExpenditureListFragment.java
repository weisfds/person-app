package com.example.tally.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tally.R;
import com.example.tally.adapter.BaseRecyclerAdapter;
import com.example.tally.adapter.SmartViewHolder;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.layout.simple_list_item_2;
import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * 支出列表Fragment
 */
public class ExpenditureListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private RefreshLayout mRefreshLayout;

    private RecyclerView mRecyclerView;

    private BaseRecyclerAdapter<Item> mAdpater;
    //第一次自动刷新
    private static boolean isFirstEnter = true;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void autoRreshLayout(){
        mRefreshLayout.autoRefresh();
    }

    private enum Item {
        尺寸拉伸("尺寸拉伸"),
        位置平移("位置平移"),
        背后固定("背后固定"),
        显示时间("显示时间");
        public String nameId;
        Item(String nameId) {
            this.nameId = nameId;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.expe_list_rv);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置mRecyclerView内容
        List<Item> items = new ArrayList<>();
        items.addAll(Arrays.asList(Item.values()));
        mRecyclerView.setAdapter(mAdpater = new BaseRecyclerAdapter<Item>(items, simple_list_item_2,this) {
            //展示数据
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(android.R.id.text1, model.name());
                holder.text(android.R.id.text2, model.nameId);
                holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
            }
        });

        if (isFirstEnter) {
            isFirstEnter = false;
            //触发自动刷新
            mRefreshLayout.autoRefresh();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expenditure_list, container, false);
    }
}
