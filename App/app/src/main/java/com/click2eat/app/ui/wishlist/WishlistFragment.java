package com.click2eat.app.ui.wishlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.click2eat.app.R;
import com.click2eat.app.ui.LoadListTask;
import com.click2eat.app.ui.OnRestaurantClickedListener;
import com.click2eat.app.ui.SimpleAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment {

    private static SimpleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    protected static List<String> wishlist;
    private Context context;
    protected static OnRestaurantClickedListener listener;
    private FirebaseAuth mAuth;

    public static SimpleAdapter getmAdapter() {
        return mAdapter;
    }

    public static List<String> getWishlist() {
        return wishlist;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        wishlist = new ArrayList<>();
        mAdapter = new SimpleAdapter(context, wishlist,getActivity(),mAuth.getCurrentUser().getUid());
        LoadListTask lwt=new LoadListTask(getActivity(),wishlist,mAdapter,mAuth.getCurrentUser().getUid(),"wishlist");
        lwt.execute();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.list_restaurants, container, false);
        mRecyclerView = mContentView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContentView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

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