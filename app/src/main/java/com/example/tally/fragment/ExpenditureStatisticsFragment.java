package com.example.tally.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tally.R;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * 支出统计Fragment
 */

public class ExpenditureStatisticsFragment extends Fragment {

    private RefreshLayout mRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expenditure_list, container, false);
    }
}