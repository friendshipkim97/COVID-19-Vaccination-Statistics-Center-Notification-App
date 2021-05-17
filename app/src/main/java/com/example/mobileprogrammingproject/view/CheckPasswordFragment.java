package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileprogrammingproject.databinding.FragmentCheckPasswordBinding;
import com.example.mobileprogrammingproject.databinding.FragmentQNAWriteBinding;

public class CheckPasswordFragment extends Fragment {

    private View view;
    private FragmentCheckPasswordBinding mBinding;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCheckPasswordBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        return view;
    }

    public static CheckPasswordFragment newInstance() {
        return new CheckPasswordFragment();
    }
}