package com.example.mobileprogrammingproject.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingproject.constants.Constants.ESearchEmail;
import com.example.mobileprogrammingproject.contract.SearchEmailContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.valueObject.VSearchEmailChild;
import com.example.mobileprogrammingproject.databinding.ActivitySearchEmailBinding;
import com.example.mobileprogrammingproject.view.SearchEmailActivity;


import java.util.ArrayList;

public class SearchEmailPresenter implements SearchEmailContract.Presenter{

    // Name And Birth Attributes
    private ArrayList<VSearchEmailChild> arrayListNameAndBirth;
    private VSearchEmailChild VSearchEmailChildNAB;
    private RecyclerView.Adapter adapterNameAndBirth;

    // Name And Phone Attributes
    private ArrayList<VSearchEmailChild> arrayListNameAndPhone;
    private VSearchEmailChild VSearchEmailChildNAP;
    private RecyclerView.Adapter adapterNameAndPhone;

    // Other Attributes
    private SearchEmailActivity searchEmailActivity;
    private ActivitySearchEmailBinding mBinding;
    private AppDatabase mAppDatabase;

    // Constructor
    public SearchEmailPresenter(SearchEmailActivity searchEmailActivity, ActivitySearchEmailBinding mBinding, AppDatabase mAppDatabase,
                                ArrayList<VSearchEmailChild> arrayListNameAndBirth, ArrayList<VSearchEmailChild> arrayListNameAndPhone,
                                RecyclerView.Adapter adapterNameAndBirth, RecyclerView.Adapter adapterNameAndPhone) {
         this.searchEmailActivity = searchEmailActivity;
         this.mBinding = mBinding;
         this.mAppDatabase = mAppDatabase;
         this.arrayListNameAndBirth = arrayListNameAndBirth;
         this.arrayListNameAndPhone = arrayListNameAndPhone;
         this.adapterNameAndBirth = adapterNameAndBirth;
         this.adapterNameAndPhone = adapterNameAndPhone;
         VSearchEmailChildNAB = new VSearchEmailChild(ESearchEmail.searchEmailNABText1.getText(),
                 ESearchEmail.searchEmailNABText2.getText(), ESearchEmail.searchEmailNABText3.getText());
         VSearchEmailChildNAP = new VSearchEmailChild(ESearchEmail.searchEmailNAPText1.getText(),
                ESearchEmail.searchEmailNAPText2.getText(), ESearchEmail.searchEmailNAPText3.getText());

    }

    @Override
    public void NABClick() {
        if(arrayListNameAndBirth.size()==0) {
            arrayListNameAndBirth.add(VSearchEmailChildNAB);
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
            arrayListNameAndPhone.add(VSearchEmailChildNAP);
        }
        if(arrayListNameAndBirth.size()==1) {
            arrayListNameAndBirth.remove(0);
        }
        adapterNameAndPhone.notifyDataSetChanged();
        adapterNameAndBirth.notifyDataSetChanged();
    }


}
