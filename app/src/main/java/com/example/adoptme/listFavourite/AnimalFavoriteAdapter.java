package com.example.adoptme.listFavourite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptme.R;

import java.util.ArrayList;

public class AnimalFavoriteAdapter extends RecyclerView.Adapter<AnimalFavoriteAdapter.AnimalViewHolder> {

    private ArrayList<AnimalFavorite> dataList;

    public AnimalFavoriteAdapter(ArrayList<AnimalFavorite> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.acitivity_list_favorites, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        holder.tvImg.setImageResource(dataList.get(position).getImg());
        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvDescription.setText(dataList.get(position).getDescription());
        holder.tvLocation.setText(dataList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle, tvDescription, tvLocation;
        private ImageView tvImg;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);

            tvImg = itemView.findViewById(R.id.imgFavorite);
            tvTitle = itemView.findViewById(R.id.animalFavName);
            tvDescription = itemView.findViewById(R.id.AnimalFavDes);
            tvLocation = itemView.findViewById(R.id.animalFavLocation);
        }
    }
}
