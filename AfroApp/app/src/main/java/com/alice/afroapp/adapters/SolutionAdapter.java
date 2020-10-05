package com.alice.afroapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alice.afroapp.R;

import java.util.List;
import java.util.Map;

public class SolutionAdapter extends RecyclerView.Adapter<SolutionAdapter.SolutionViewHolder> {

    private List<Map<String,String>> mList;


    public SolutionAdapter (List<Map<String,String>> list){
        mList = list;
    }

    @NonNull
    @Override
    public SolutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_solution, parent, false);
        return new SolutionViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull SolutionViewHolder holder, int position) {

        Map<String,String> map = mList.get(position);
        holder.sNameTextview.setText(map.get("userName"));
        holder.solutionTextview.setText(map.get("title"));

    }

    @Override
    public int getItemCount() {

        return mList.size();

    }

    public static class SolutionViewHolder extends RecyclerView.ViewHolder{

        public TextView solutionTextview;
        public TextView sNameTextview;

        public SolutionViewHolder(@NonNull View itemView) {

            super(itemView);
            solutionTextview = (TextView) itemView.findViewById(R.id.solution_title);
            sNameTextview = (TextView) itemView.findViewById(R.id.s_user_name);

        }
    }
}
