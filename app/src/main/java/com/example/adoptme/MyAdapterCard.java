package com.example.adoptme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapterCard extends PagerAdapter {

    private Context context;
    private ArrayList<MyModelCard> modelArrayList;

    public MyAdapterCard(Context context, ArrayList<MyModelCard> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // inflate layout card_item
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);

        // init id view
        ImageView bannerTv = view.findViewById(R.id.bannerTv);
        TextView titleTv = view.findViewById(R.id.titleTv);
        TextView descriptionTv = view.findViewById(R.id.descriptionTv);
        TextView dateTv = view.findViewById(R.id.dateTv);

        // get data
        MyModelCard modelCard = modelArrayList.get(position);
        String title = modelCard.getTitle();
        String description = modelCard.getDescription();
        String date = modelCard.getDate();
        int image = modelCard.getImage();

        // set data
        bannerTv.setImageResource(image);
        titleTv.setText(title);
        descriptionTv.setText(description);
        dateTv.setText(date);

        //handle card click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, title+"\n"+description, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view, position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
