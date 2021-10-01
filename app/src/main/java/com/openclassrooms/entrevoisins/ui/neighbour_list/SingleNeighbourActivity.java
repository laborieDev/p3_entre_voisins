package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SingleNeighbourActivity extends AppCompatActivity {

    public static final String EXTRA_INT = "extra.int";

    /***** Lajout Contents *****/
    @BindView(R.id.user_name)
    public TextView mUserName;
    @BindView(R.id.user_address)
    public TextView mUserAddress;
    @BindView(R.id.user_phone)
    public TextView mUserPhone;
    @BindView(R.id.user_web)
    public TextView mUserWeb;
    @BindView(R.id.user_description)
    public TextView mUserDescription;

    /***** Get Neighbour Variables *****/
    int position;
    NeighbourApiService mApiService;
    Neighbour mNeighbourSelected;

    //public boolean isFavorite = false;
    //private String favTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_neighbour);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();

        Intent intent = getIntent();
        position = intent.getIntExtra(SingleNeighbourActivity.EXTRA_INT, 0);

        mNeighbourSelected = mApiService.getNeighbour(position);

        mUserName.setText(mNeighbourSelected.getName());
        mUserAddress.setText(mNeighbourSelected.getAddress());
        mUserPhone.setText(mNeighbourSelected.getPhoneNumber());
        mUserWeb.setText("www.facebook.fr/" + mNeighbourSelected.getName().toLowerCase());
        mUserDescription.setText(mNeighbourSelected.getAboutMe());
    }


    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity, int position) {
        Intent intent = new Intent(activity, SingleNeighbourActivity.class);
        intent.putExtra(SingleNeighbourActivity.EXTRA_INT, position);
        ActivityCompat.startActivity(activity, intent, null);
    }
}