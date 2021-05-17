package com.example.mobileprogrammingproject.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileprogrammingproject.databinding.ActivityMainDrawerBinding;
import com.example.mobileprogrammingproject.databinding.FragmentHomeBinding;

public class MenuFragment extends Fragment {

    private View view;
    private ActivityMainDrawerBinding mBinding;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ActivityMainDrawerBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        mBinding.tvExam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext().getApplicationContext(), "버튼클릭", Toast.LENGTH_SHORT).show();
                mBinding.tvExam3.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }
}
