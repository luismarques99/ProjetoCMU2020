package com.click2eat.app.ui.favorites;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.click2eat.app.R;
import com.click2eat.app.ui.LoadListTask;
import com.click2eat.app.ui.OnRestaurantClickedListener;
import com.click2eat.app.ui.SimpleAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private static SimpleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    protected static List<String> favorites;
    private Context context;
    protected static OnRestaurantClickedListener listener;
    private FirebaseAuth mAuth;

    public static SimpleAdapter getmAdapter() {
        return mAdapter;
    }

    public static List<String> getFavorites() {
        return favorites ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        favorites = new ArrayList<>();
        mAdapter = new SimpleAdapter(context, favorites, getActivity(), mAuth.getCurrentUser().getUid());
        LoadListTask lft = new LoadListTask(getActivity(), favorites, mAdapter, mAuth.getCurrentUser().getUid(),"favorite");
        lft.execute();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.list_restaurants, container, false);
        mRecyclerView = mContentView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContentView.getContext()));
        mRecyclerView.setAdapter(mAdapter);

        return mContentView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnRestaurantClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRestaurantClickedClicked");
        }
    }
}
