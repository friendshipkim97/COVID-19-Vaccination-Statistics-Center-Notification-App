package com.example.mobileprogrammingproject.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileprogrammingproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private View view;
    private FragmentHomeBinding mBinding;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        return view;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

}
