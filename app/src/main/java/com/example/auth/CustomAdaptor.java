package com.example.auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.auth.ImageNicer.decodeSampledBitmapFromResource;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

    private Context context;
    private List<App> apps;
    private OnNoteListener mOnNoteListener;

    public CustomAdaptor(Context context, List<App> apps, OnNoteListener onNoteListener) {
        this.context = context;
        this.apps = apps;
        this.mOnNoteListener= onNoteListener;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImage;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            this.onNoteListener= onNoteListener;
        }


        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_layout,parent,false);

        return new MyViewHolder(v,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        App app = apps.get(position);
        holder.mImage.setImageBitmap(ImageNicer.decodeSampledBitmapFromResource(context.getResources(),app.getImage(),100,123));

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }


}
