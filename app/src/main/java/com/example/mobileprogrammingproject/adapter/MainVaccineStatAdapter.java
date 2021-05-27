package com.example.mobileprogrammingproject.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mobileprogrammingproject.constants.Constants.EMainVaccineStatAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.QnA;
import com.example.mobileprogrammingproject.model.VaccineStat;
import com.example.mobileprogrammingproject.view.VaccineStatDetailed;

import java.util.ArrayList;
import java.util.Vector;

public class MainVaccineStatAdapter extends RecyclerView.Adapter<MainVaccineStatAdapter.CustomViewHolder>{

    private Vector<VaccineStat> vaccineStats;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;

    public MainVaccineStatAdapter(FragmentActivity fragmentContext) {
        this.fragmentContext = fragmentContext;
    }

    @NonNull
    @Override
    public MainVaccineStatAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_vaccine_stat_view, parent, false);
        MainVaccineStatAdapter.CustomViewHolder holder = new MainVaccineStatAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainVaccineStatAdapter.CustomViewHolder holder, int position) {
        holder.sido.setText(vaccineStats.get(position).getSido());
        holder.firstCnt.setText(String.valueOf(vaccineStats.get(position).getFirstCnt()));
        holder.secondCnt.setText(String.valueOf(vaccineStats.get(position).getSecondCnt()));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleClickCheck(position);
            }
        });
    }

    private void doubleClickCheck(int position) {

        if (System.currentTimeMillis() > btnPressTime + Integer.valueOf(EMainVaccineStatAdapter.clickTerm.getText())) {
            btnPressTime = System.currentTimeMillis();
            Toast.makeText(fragmentContext.getApplicationContext(), EMainVaccineStatAdapter.clickDetailed.getText(),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= btnPressTime + Integer.valueOf(EMainVaccineStatAdapter.clickTerm.getText())) {
            replaceSetBundleFragment(VaccineStatDetailed.newInstance(), vaccineStats.get(position));

        }
    }

    @Override
    public int getItemCount() {
        return (vaccineStats != null ? vaccineStats.size() : 0);

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView sido;
        TextView firstCnt;
        TextView secondCnt;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.sido = itemView.findViewById(R.id.tv_recyclerSido);
            this.firstCnt = itemView.findViewById(R.id.tv_recyclerFirstCnt);
            this.secondCnt = itemView.findViewById(R.id.tv_recyclerSecondCnt);
        }
    }

    public void addVaccineStats(Vector<VaccineStat> vaccineStats){
        this.vaccineStats = vaccineStats;
    }

    public void replaceSetBundleFragment(Fragment fragment, VaccineStat vaccineStat){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EMainVaccineStatAdapter.vaccineStat.getText(), vaccineStat);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }
}
