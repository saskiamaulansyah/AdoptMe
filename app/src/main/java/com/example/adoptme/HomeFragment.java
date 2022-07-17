package com.example.adoptme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //ui views
    private ViewPager viewPager;

    private ArrayList<MyModelCard> myModelArrayList;
    private MyAdapterCard myAdapterCard;

    private CardView btn_favarite;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//
    }

    private void loadCards() {
        myModelArrayList = new ArrayList<>();
        myModelArrayList.add(new MyModelCard("Title 01","desc 01", "03/08/2022", R.drawable.banner_malang_food));
        myModelArrayList.add(new MyModelCard("Title 02","desc 02", "03/08/2022", R.drawable.dota));
        myModelArrayList.add(new MyModelCard("Title 03","desc 03", "03/08/2022", R.drawable.dota2));
        myModelArrayList.add(new MyModelCard("Title 04","desc 04", "03/08/2022", R.drawable.valorant));
        myModelArrayList.add(new MyModelCard("Title 05","desc 05", "03/08/2022", R.drawable.valorant2));

        myAdapterCard = new MyAdapterCard(getContext(), myModelArrayList);

        viewPager.setAdapter(myAdapterCard);
        viewPager.setPadding(100,0,100,0);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = rootView.findViewById(R.id.viewPager);
        loadCards();

        btn_favarite = rootView.findViewById(R.id.btn_favorite);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                btn_favarite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }
}