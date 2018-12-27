package com.mbf5923.test.testapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.models.contentsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class contentsAdapter extends RecyclerView.Adapter<contentsAdapter.MyViewHolder> {
    private List<contentsModel> mainitemModelList;
    private static ClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.tvtitle)
        TextView tvtitle;
        @BindView(R.id.imglogo)
        ImageView imglogo;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        contentsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public contentsAdapter(List<contentsModel> moviesList) {
        this.mainitemModelList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.content_item, parent, false);

        return new MyViewHolder(v);
    }
    public abstract void load();
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if ((position >= getItemCount() - 1))
            load();
        contentsModel movie = mainitemModelList.get(position);
        holder.tvtitle.setText(movie.getTitle());
        Glide.with(holder.itemView.getContext()).load(movie.getThumbnailUrl()).into(holder.imglogo);




    }

    @Override
    public int getItemCount() {

        return mainitemModelList.size();
    }
}
