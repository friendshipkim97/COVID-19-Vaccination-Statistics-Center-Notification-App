package com.example.mobileprogrammingproject.presenter;

import android.util.Log;

import com.example.mobileprogrammingproject.contract.QnACheckContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentQNACheckBinding;
import com.example.mobileprogrammingproject.model.QnA;

import java.util.ArrayList;
import java.util.List;

public class QnACheckPresenter implements QnACheckContract.Presenter{

    private FragmentQNACheckBinding mBinding;
    private AppDatabase mAppDatabase;
    private QnACheckContract.View qnACheckView;

    public QnACheckPresenter(AppDatabase mAppDatabase, FragmentQNACheckBinding mBinding, QnACheckContract.View qnACheckView) {
        this.mAppDatabase = mAppDatabase;
        this.mBinding = mBinding;
        this.qnACheckView = qnACheckView;

    }

    @Override
    public ArrayList<QnA> getData(String strEmail, String strEmailType) {
         int userId = this.mAppDatabase.userDao().findIdByEmailAndType(strEmail, strEmailType);
        List<QnA> list = this.mAppDatabase.qnADao().findAllQnAByuserId(userId);
        ArrayList<QnA> arrayList = (ArrayList<QnA>) list;
       Log.e("확인",arrayList.get(0).getTitle());
        return arrayList;
    }

}
