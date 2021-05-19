package com.example.mobileprogrammingproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileprogrammingproject.model.QnA;
import com.example.mobileprogrammingproject.R;

import java.util.ArrayList;

public class QNACustomAdapter extends RecyclerView.Adapter<QNACustomAdapter.CustomViewHolder> {

    private ArrayList<QnA> arrayList;
    private Context context;
    private String strEmail;

    public QNACustomAdapter(ArrayList<QnA> arrayList, Context context, String strEmail) {
        this.arrayList = arrayList;
        this.context = context;
        this.strEmail = strEmail;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qna_check_list_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.email.setText(strEmail);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.content.setText(arrayList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView title;
        TextView content;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.email = itemView.findViewById(R.id.tv_writerEmail2);
            this.title = itemView.findViewById(R.id.tv_qnatitle);
            this.content = itemView.findViewById(R.id.tv_qnacontent2);
        }
    }
}
