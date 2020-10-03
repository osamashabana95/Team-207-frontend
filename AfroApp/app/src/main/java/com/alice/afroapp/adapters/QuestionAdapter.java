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

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Map<String,String>> mList;

    public QuestionAdapter (List<Map<String,String>> list){

        mList = list;

    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_question, parent, false);
        return new QuestionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        Map<String,String> map = mList.get(position);
        holder.qNameTextview.setText(map.get("userName"));
        holder.questionTextview.setText(map.get("title"));

    }

    @Override
    public int getItemCount() {

        return mList.size();

    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder{

        public TextView questionTextview;
        public TextView qNameTextview;

        public QuestionViewHolder(@NonNull View itemView) {

            super(itemView);
            questionTextview = (TextView) itemView.findViewById(R.id.question_title);
            qNameTextview = (TextView) itemView.findViewById(R.id.q_user_name);

        }
    }
}
