package com.example.mobileprogrammingproject.presenter;

import com.example.mobileprogrammingproject.contract.QnAWriteContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentQNAWriteBinding;
import com.example.mobileprogrammingproject.model.QnA;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QnAWritePresenter implements QnAWriteContract.Presenter {

    private FragmentQNAWriteBinding mBinding;
    private AppDatabase mAppDatabase;
    private QnAWriteContract.View qnAWriteView;

    public QnAWritePresenter(AppDatabase mAppDatabase, FragmentQNAWriteBinding mBinding, QnAWriteContract.View qnAWriteView) {
        this.mAppDatabase = mAppDatabase;
        this.mBinding = mBinding;
        this.qnAWriteView = qnAWriteView;
    }

    @Override
    public void submit(String strEmail, String strEmailType) {
        int userId = mAppDatabase.userDao().findIdByEmailAndType(strEmail, strEmailType);
        long mNow = System.currentTimeMillis();
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date mDate = new Date(mNow);
        String date = mFormat.format(mDate);
        QnA qnA = new QnA(userId, mBinding.etContent.getText().toString(), date, mBinding.tvQnaWriteTitle.getText().toString());
        this.mAppDatabase.qnADao().insert(qnA);
        qnAWriteView.showToast("작성을 완료했습니다.");

    }

}
