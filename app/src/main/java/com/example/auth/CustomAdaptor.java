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

    public CustomAdaptor(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_layout,parent,false);

        return new MyViewHolder(v);
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


}
