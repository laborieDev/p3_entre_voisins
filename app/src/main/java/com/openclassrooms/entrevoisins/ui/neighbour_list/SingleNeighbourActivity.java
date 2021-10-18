package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SingleNeighbourActivity extends AppCompatActivity {

    /***** Layout Contents *****/
    @BindView(R.id.user_avatar)
    public ImageView mUserAvatar;
    @BindView(R.id.user_name)
    public TextView mUserName;
    @BindView(R.id.user_name_img)
    public TextView mUserNameImg;
    @BindView(R.id.user_address)
    public TextView mUserAddress;
    @BindView(R.id.user_phone)
    public TextView mUserPhone;
    @BindView(R.id.user_web)
    public TextView mUserWeb;
    @BindView(R.id.user_description)
    public TextView mUserDescription;

    /***** Get Neighbour Object *****/
    Neighbour mNeighbourSelected;

    //public boolean isFavorite = false;
    //private String favTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_neighbour);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mNeighbourSelected = (Neighbour) intent.getSerializableExtra("Neighbour");

        Glide.with(mUserAvatar.getContext())
                .load(mNeighbourSelected.getAvatarUrl())
                .into(mUserAvatar);

        mUserNameImg.setText(mNeighbourSelected.getName());
        mUserName.setText(mNeighbourSelected.getName());
        mUserAddress.setText(mNeighbourSelected.getAddress());
        mUserPhone.setText(mNeighbourSelected.getPhoneNumber());
        mUserWeb.setText("www.facebook.fr/" + mNeighbourSelected.getName().toLowerCase());
        mUserDescription.setText(mNeighbourSelected.getAboutMe());
    }

    @OnClick(R.id.add_to_favorites)
    void addToFavorites()
    {
        String message;

        if (mNeighbourSelected.getIsFavorite()) {
            mNeighbourSelected.isNotFavorite();
            message = mNeighbourSelected.getName() + " a bien été retiré(e) de vos favoris";
        } else {
            mNeighbourSelected.isFavorite();
            message = mNeighbourSelected.getName() + " a bien été ajouté(e) à vos favoris";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.go_to_list)
    void goToList() {
        Intent intent = new Intent(this, ListNeighbourActivity.class);
        startActivity(intent);
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity, Neighbour neighbour) {
        Intent intent = new Intent(activity, SingleNeighbourActivity.class);
        //intent.putExtra(SingleNeighbourActivity.EXTRA_INT, position);
        intent.putExtra("Neighbour", neighbour);
        ActivityCompat.startActivity(activity, intent, null);
    }
}