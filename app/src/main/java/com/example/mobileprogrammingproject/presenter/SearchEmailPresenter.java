package com.example.mobileprogrammingproject.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingproject.dao.AppDatabase;
import com.example.mobileprogrammingproject.valueObject.SearchEmailChild;
import com.example.mobileprogrammingproject.databinding.ActivitySearchEmailBinding;
import com.example.mobileprogrammingproject.view.SearchEmailActivity;

import java.util.ArrayList;

public class SearchEmailPresenter implements SearchEmailContract.Presenter{

    // Name And Birth Attributes
    private ArrayList<SearchEmailChild> arrayListNameAndBirth;
    private SearchEmailChild searchEmailChildNAB;
    private RecyclerView.Adapter adapterNameAndBirth;

    // Name And Phone Attributes
    private ArrayList<SearchEmailChild> arrayListNameAndPhone;
    private SearchEmailChild searchEmailChildNAP;
    private RecyclerView.Adapter adapterNameAndPhone;

    // Other Attributes
    private SearchEmailActivity searchEmailActivity;
    private ActivitySearchEmailBinding mBinding;
    private AppDatabase mAppDatabase;

    // Constructor
    public SearchEmailPresenter(SearchEmailActivity searchEmailActivity, ActivitySearchEmailBinding mBinding, AppDatabase mAppDatabase,
                                ArrayList<SearchEmailChild> arrayListNameAndBirth, ArrayList<SearchEmailChild> arrayListNameAndPhone,
                                RecyclerView.Adapter adapterNameAndBirth, RecyclerView.Adapter adapterNameAndPhone) {
         this.searchEmailActivity = searchEmailActivity;
         this.mBinding = mBinding;
         this.mAppDatabase = mAppDatabase;
         this.arrayListNameAndBirth = arrayListNameAndBirth;
         this.arrayListNameAndPhone = arrayListNameAndPhone;
         this.adapterNameAndBirth = adapterNameAndBirth;
         this.adapterNameAndPhone = adapterNameAndPhone;
         searchEmailChildNAB = new SearchEmailChild("이름을 입력하세요", "생년월일을 입력하세요 ex)1997년4월30일", "아이디찾기");
         searchEmailChildNAP = new SearchEmailChild("이름을 입력하세요", "휴대폰 번호를 -포함해 입력하세요", "아이디찾기");

    }

    @Override
    public void NABClick() {
        if(arrayListNameAndBirth.size()==0) {
            arrayListNameAndBirth.add(searchEmailChildNAB);
        }
        if(arrayListNameAndPhone.size()==1) {
            arrayListNameAndPhone.remove(0);
        }
        adapterNameAndPhone.notifyDataSetChanged();
        adapterNameAndBirth.notifyDataSetChanged();
    }

    @Override
    public void NAPClick() {
        if(arrayListNameAndPhone.size()==0) {
            arrayListNameAndPhone.add(searchEmailChildNAP);
        }
        if(arrayListNameAndBirth.size()==1) {
            arrayListNameAndBirth.remove(0);
        }
        adapterNameAndPhone.notifyDataSetChanged();
        adapterNameAndBirth.notifyDataSetChanged();
    }


}
