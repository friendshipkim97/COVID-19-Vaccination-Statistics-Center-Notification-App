package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.FragmentCenterDetailedBinding;
import com.example.mobileprogrammingproject.databinding.FragmentWebViewBinding;
import com.example.mobileprogrammingproject.model.Center;

public class WebViewFragment extends Fragment {

    private View view;
    private FragmentWebViewBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;

    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // fragment는 onCreateView로 생성하면 된다.

        // set Binding
        mBinding = FragmentWebViewBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        init();
        initToolbar();
        setHasOptionsMenu(true);

        return view;
    }

    private void init() {
        mBinding.webView.getSettings().setJavaScriptEnabled(true);
        mBinding.webView.loadUrl("https://ncvr.kdca.go.kr/cobk/index.html");
        mBinding.webView.setWebChromeClient(new WebChromeClient());
        mBinding.webView.setWebViewClient(new WebViewClientClass());
        mBinding.webView.setOnKeyListener(new View.OnKeyListener() {
            @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (mBinding.webView!=null) {
                            if (mBinding.webView.canGoBack()){
                                mBinding.webView.goBack(); } else {
                                getActivity().onBackPressed(); }
                        } } }return true; } });

    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbWebView);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_Exit:
                Bundle bundle = getArguments();
                replaceSetBundleFragment(CenterDetailedFragment.newInstance(), (Center) bundle.getSerializable("centerItem"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void goHomeFragment() {
        ((MainActivity)getActivity()).replaceFragment(HomeFragment.newInstance());
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }

    public void replaceSetBundleFragment(Fragment fragment, Center centerItem){
        Bundle bundle = new Bundle();
        bundle.putSerializable("centerItem", centerItem);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }
}