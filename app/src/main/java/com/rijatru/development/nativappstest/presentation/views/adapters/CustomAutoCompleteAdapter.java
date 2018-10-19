package com.rijatru.development.nativappstest.presentation.views.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.rijatru.development.nativappstest.data.api.model.search.get.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    private List<Search> resultList = new ArrayList<>();

    public CustomAutoCompleteAdapter(Context context, int textViewResourceId, String[] data) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return resultList.get(index).title;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                filterResults.values = resultList;
                filterResults.count = resultList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    public void setData(List<Search> data) {
        this.resultList = data;
    }

    public Search getObjectAt(int position) {
        return resultList.get(position);
    }
}
