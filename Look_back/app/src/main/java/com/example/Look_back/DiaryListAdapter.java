package com.example.Look_back;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Look_back.R;

import java.util.ArrayList;
import java.util.List;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.MyViewHolder> implements Filterable {

    private List<DiaryModel> mList =null ;
    private List<DiaryModel> mDataListAll;
    private Context context;
    public DiaryListAdapter(Context context, List<DiaryModel> items) {
        this.context =context;
        this.mList = items;
        mDataListAll = new ArrayList<>(items);
    }

    @Override
    public DiaryListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.list_item, parent, false) ;
        DiaryListAdapter.MyViewHolder vh = new DiaryListAdapter.MyViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        DiaryModel diaryModel = mList.get(position) ;
        holder.date.setText(diaryModel.getDate());
        holder.diary.setText(diaryModel.getDiary());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView diary;

        MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_text);
            diary = itemView.findViewById(R.id.diary_text);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DiaryModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DiaryModel item : mDataListAll) {
                    if (item.getDiary().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
