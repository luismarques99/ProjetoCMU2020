//package com.click2eat.app.ui.live;
//
//import android.app.Activity;
//import android.content.Context;
//import android.location.Location;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.cmuteam.app.Models.TestRestaurant;
//import com.cmuteam.app.R;
//import com.google.firebase.auth.FirebaseAuth;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import com.google.android.gms.location.FusedLocationProviderClient;
////import com.google.android.gms.location.LocationServices;
//
//public class RestaurantsListFragment extends Fragment {
//    private RestaurantAdapter mAdapter;
//    private RecyclerView mRecyclerView;
//    //    protected static List<Restaurant_> restaurantsList;
//    protected static List<TestRestaurant> restaurantsList;
//    protected static Location myLocation;
//    private Context context;
//    protected static OnRestaurantClickedListener listener;
//    private FirebaseAuth mAuth;
//    private static final int REQUEST_FINE_LOCATION = 100;
////    private FusedLocationProviderClient mFusedLocationClient;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        context = getContext();
//        mAuth = FirebaseAuth.getInstance();
//        restaurantsList = new ArrayList<>();
//        for (int i = 0; i < 30; i++)
//            restaurantsList.add(new TestRestaurant(i + 1, "Restaurant" + (i + 1)));
////        getLastLocation();
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View mContentView = inflater.inflate(R.layout.restaurants_list, container, false);
//        mRecyclerView = mContentView.findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContentView.getContext()));
//
//        return mContentView;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            listener = (OnRestaurantClickedListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OnButtonClicked");
//        }
//    }
//
////    private Retrofit getRetrofit() {
////        return new Retrofit.Builder()
////                .baseUrl("https://developers.zomato.com/api/v2.1/")
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////    }
////
////    private ZomatoApi getApi() {
////        return getRetrofit().create(ZomatoApi.class);
////    }
////
////    private void getLastLocation() {
////        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) !=
////                PackageManager.PERMISSION_GRANTED) {
////            requestPermissions( //Method of Fragment
////                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
////                    REQUEST_FINE_LOCATION
////            );
////        } else {
////            getRestaurants();
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        if (requestCode == REQUEST_FINE_LOCATION) {
////            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
////                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                getRestaurants();
////            }
////        }
////    }
////
////
////    private void getRestaurants() {
////        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
////        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
////            @Override
////            public void onSuccess(final Location location) {
////                if (location != null) {
////                    getApi().getNearbyRestaurants("75be9f9e2239fe637bf9cb1b46979d91", location.getLatitude(), location.getLongitude(),
////                            20, 10000, "rating", "desc").enqueue(new Callback<ApiResponse>() {
////                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
////                            List<Restaurant> restaurants = response.body().getRestaurants();
////                            mAdapter = new RestaurantAdapter(context, restaurantsList);
////                            mRecyclerView.setAdapter(mAdapter);
////                            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
////                            mRecyclerView.addItemDecoration(itemDecoration);
////                            for (int i = 0; i < restaurants.size(); i++) {
////                                double lat = Double.parseDouble(restaurants.get(i).getRestaurant().getLocation().getLatitude());
////                                double lon = Double.parseDouble(restaurants.get(i).getRestaurant().getLocation().getLongitude());
////                                double distance = calculateDistance(location.getLatitude(), location.getLongitude(), lat, lon);
////                                distance = (double) Math.round(distance * 100d) / 100d;
////                                restaurants.get(i).getRestaurant().setDistance(distance);
////                                restaurantsList.add(restaurants.get(i).getRestaurant());
////                                mAdapter.notifyItemInserted(i);
////                            }
////                        }
////
////                        @Override
////                        public void onFailure(Call<ApiResponse> call, Throwable throwable) {
////                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////                            builder.setMessage("Couldn't find any nearby restaurants");
////                            AlertDialog mDialog = builder.create();
////                            mDialog.show();
////                        }
////                    });
////
////                }
////            }
////        }).addOnFailureListener(getActivity(), new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                Toast.makeText(getActivity(), "It wasn't possible to determine your location", Toast.LENGTH_LONG).show();
////            }
////        });
////    }
////
////    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
////        if ((lat1 == lat2) && (lon1 == lon2)) {
////            return 0;
////        } else {
////            double theta = lon1 - lon2;
////            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
////            dist = Math.acos(dist);
////            dist = Math.toDegrees(dist);
////            dist = dist * 60 * 1.1515;
////            dist = dist * 1.609344;
////            return dist;
////        }
////    }
//}
