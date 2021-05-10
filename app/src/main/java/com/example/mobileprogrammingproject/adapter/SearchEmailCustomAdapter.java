package com.example.mobileprogrammingproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.dao.AppDatabase;
import com.example.mobileprogrammingproject.databinding.SearchEmailChildListViewBinding;
import com.example.mobileprogrammingproject.presenter.SearchEmailContract;
import com.example.mobileprogrammingproject.valueObject.VSearchEmailChild;
import com.example.mobileprogrammingproject.constants.Constants.ESearchEmailCustomAdapter;

import java.util.ArrayList;

public class SearchEmailCustomAdapter extends RecyclerView.Adapter<SearchEmailCustomAdapter.CustomViewHolder>{

    private ArrayList<VSearchEmailChild> arrayList;
    private Context context;
    private SearchEmailChildListViewBinding mBinding;
    private SearchEmailContract.View searchEmailView;
    private AppDatabase mAppDatabase;

    public SearchEmailCustomAdapter(ArrayList<VSearchEmailChild> arrayList, Context context, SearchEmailChildListViewBinding mBinding, SearchEmailContract.View searchEmailView, AppDatabase mAppDatabase) {
        this.arrayList = arrayList;
        this.context = context;
        this.mBinding = mBinding;
        this.searchEmailView = searchEmailView;
        this.mAppDatabase = mAppDatabase;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_email_child_list_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {

        holder.et_recycle1.setHint(arrayList.get(position).getEt_recycle1());
        holder.et_recycle2.setHint(arrayList.get(position).getEt_recycle2());
        holder.btn_recycle3.setText(arrayList.get(position).getEt_recycle3());
        holder.itemView.setTag(position);

        holder.btn_recycle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.get(position).getEt_recycle2().contains(ESearchEmailCustomAdapter.dateOfBirth.getText())){
                    searchEmailCheckNAB(holder);
                }
                else if(arrayList.get(position).getEt_recycle2().contains(ESearchEmailCustomAdapter.phone.getText())){
                    searchEmailCheckNAP(holder);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
         EditText et_recycle1;
         EditText et_recycle2;
         Button btn_recycle3;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.et_recycle1 = itemView.findViewById(R.id.et_searchEmailChild1);
            this.et_recycle2 = itemView.findViewById(R.id.et_searchEmailChild2);
            this.btn_recycle3 = itemView.findViewById(R.id.btn_searchEmailChild);

        }
    }

    private boolean searchEmailCheckNAP(CustomViewHolder holder) {
        if (validNameCheck(holder) == false) {
            return false;
        } else {
            String email = mAppDatabase.userDao().findEmailByNameAndPhone(holder.et_recycle1.getText().toString(), holder.et_recycle2.getText().toString());
            if(email==null){
                searchEmailView.showToast(ESearchEmailCustomAdapter.noMatchingEmail.getText());
                return false;
            } else {
                searchEmailView.showToast(ESearchEmailCustomAdapter.notificationMessage1.getText() + email + ESearchEmailCustomAdapter.notificationMessage2.getText());
                return true;
            }
        }
    }

    public boolean searchEmailCheckNAB(CustomViewHolder holder) {

        if (validNameCheck(holder) == false) {
            return false;
        } else {
            String email = mAppDatabase.userDao().findEmailByNameAndBirth(holder.et_recycle1.getText().toString(), holder.et_recycle2.getText().toString());
            if(email==null){
                searchEmailView.showToast(ESearchEmailCustomAdapter.noMatchingEmail.getText());
                return false;
            } else {
                searchEmailView.showToast(ESearchEmailCustomAdapter.notificationMessage1.getText() + email + ESearchEmailCustomAdapter.notificationMessage2.getText());
                return true;
            }
        }
    }

    public boolean validNameCheck(CustomViewHolder holder) {
        String inputName = holder.et_recycle1.getText().toString();
        if(inputName.length()==0){
            searchEmailView.showToast(ESearchEmailCustomAdapter.inputName.getText());
            return false;
        }
        return true;
    }

}
