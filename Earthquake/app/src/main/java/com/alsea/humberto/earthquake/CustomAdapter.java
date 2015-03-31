package com.alsea.humberto.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by humberto on 3/30/15.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<Info> data;
    Context context;
    LayoutInflater mInflater;

    public CustomAdapter(Context _context, ArrayList _data){
        this.data = _data;
        this.context = _context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CompleteListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_layout, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.tv1.setText(data.get(position).getMagnitude());
        viewHolder.tv2.setText(data.get(position).getPlace());
        viewHolder.tv3.setText(data.get(position).getTitle());
        return v;
    }

    class CompleteListViewHolder {
        public TextView tv1, tv2, tv3;

        public CompleteListViewHolder(View base) {
            tv1 = (TextView) base.findViewById(R.id.textView1);
            tv2 = (TextView) base.findViewById(R.id.textView2);
            tv3 = (TextView) base.findViewById(R.id.textView3);
        }

    }

    }
