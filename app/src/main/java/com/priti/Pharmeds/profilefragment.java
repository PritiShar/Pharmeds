package com.priti.Pharmeds;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class profilefragment extends Fragment {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    ImageView imageView;
    FirebaseUser mUser = auth.getCurrentUser();


    public profilefragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profilefragment, container, false);
        auth = FirebaseAuth.getInstance();
        //Toolbar toolbar = view.findViewById(R.id.toolbar);
       // toolbar.setTitle("Account");
        //AppCompatActivity activity = (AppCompatActivity) getActivity();
       // activity.setSupportActionBar(toolbar);
//        view.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int cx = (view.getLeft() + view.getRight()) / 2;
//                int cy = (view.getTop() + view.getBottom()) / 2;
//                int finalRadius = Math.max(view.getWidth(), view.getHeight());
//
//                Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
//                int color = Color.parseColor("#FFECB3");
//                view.setBackgroundColor(color);
//                anim.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
////                animateButtonsIn();
//                    }
//                });
//                anim.start();
//            }
//        }, 0);
        TextView ttname = view.findViewById(R.id.name1);
        TextView ttemail = view.findViewById(R.id.email);
        imageView = view.findViewById(R.id.image);
        if (mUser != null) {
            String name = mUser.getDisplayName();
            String email = mUser.getEmail();

            ttname.setText(name);
            ttemail.setText(email);

        } else {
            // User is not signed in
            // ...
        }
        return view;
    }
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.add(Menu.NONE, 1, Menu.NONE, "Sign out");
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case 1:
//                auth.signOut();
//
//
//                startActivity(new Intent(getContext(), login.class));
//                break;
//        }
//        return true;
//    }
}