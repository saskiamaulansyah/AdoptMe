package com.example.adoptme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adoptme.listFavourite.AnimalFavorite;
import com.example.adoptme.listFavourite.AnimalFavoriteAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private AnimalFavoriteAdapter adapter;
    private ArrayList<AnimalFavorite> animalFavoriteArrayList;

    private int sizeAnimalList;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
    }

    void addData() {
        animalFavoriteArrayList = new ArrayList<>();
        animalFavoriteArrayList.add(new AnimalFavorite(R.drawable.dota, "Doggie Dota", "1 year | French Black Puppy", "Malang"));

        sizeAnimalList = animalFavoriteArrayList.size();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addData();
        View rootview = inflater.inflate(R.layout.fragment_favorite, container, false);

        if (sizeAnimalList != 0) {

            recyclerView = rootview.findViewById(R.id.recycleview);

            adapter = new AnimalFavoriteAdapter(animalFavoriteArrayList);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(adapter);
        }

        Toast.makeText(getContext(), String.valueOf(sizeAnimalList), Toast.LENGTH_SHORT).show();

        return rootview;
    }
}