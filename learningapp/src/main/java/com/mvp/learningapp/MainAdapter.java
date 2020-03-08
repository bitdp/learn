package com.mvp.learningapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private IRecyclerItemClickListener mListener;

    public MainAdapter(Context context, List<String> data) {
        mContext = context;
        mDatas = data;
    }

    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MainAdapter.MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface IRecyclerItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(IRecyclerItemClickListener iRecyclerItemClickListener) {
        mListener = iRecyclerItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.main_tv);
        }
    }
}
