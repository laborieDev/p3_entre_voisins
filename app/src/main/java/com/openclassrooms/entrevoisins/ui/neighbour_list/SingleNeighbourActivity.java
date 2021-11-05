package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
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
    @BindView(R.id.add_to_favorites)
    public FloatingActionButton mAddToFavorites;

    /***** Get Neighbour Object *****/
    Neighbour mNeighbourSelected;
    NeighbourApiService mApiService;

    //public boolean isFavorite = false;
    //private String favTab;

    @Override
    @SuppressLint({"RestrictedApi", "WrongConstant"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_neighbour);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();

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

        mAddToFavorites.setImageResource(getFavoriteBtnID(mNeighbourSelected.getIsFavorite()));

        mAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mNeighbourSelected.getName() + " a bien été ajouté(e) à vos favoris";
                boolean nowIsFavorite = mNeighbourSelected.getIsFavorite();

                if (nowIsFavorite) {
                    message = mNeighbourSelected.getName() + " a bien été retiré(e) de vos favoris";
                }

                mApiService.setFavorites(mNeighbourSelected, !nowIsFavorite);
                mAddToFavorites.setImageResource(getFavoriteBtnID(!nowIsFavorite));

                Toast.makeText(SingleNeighbourActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

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
        intent.putExtra("Neighbour", neighbour);
        ActivityCompat.startActivity(activity, intent, null);
    }

    /**
     * @param isFavorite
     */
    private int getFavoriteBtnID(boolean isFavorite) {
        if (isFavorite) {
            return R.drawable.ic_yellow_star;
        }

        return R.drawable.ic_star_border_yellow_24dp;
    }
}