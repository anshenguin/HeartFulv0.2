package com.example.hp.heartful;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * Created by HP INDIA on 08-Apr-17.
 */

public class FragmentTwo extends Fragment {
    public FragmentTwo(){}
  ImageButton button;
    private RecyclerView mNewsLists;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_two, container, false);
        button=(ImageButton) view.findViewById(R.id.button);
        firebaseAuth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseAuth.getCurrentUser()!=null){
                startActivity(new Intent(getActivity(),NewsPost.class));

            }
            else {
                    Toast.makeText(getActivity(),"Make sure Your are logged in for posting your own news",Toast.LENGTH_LONG).show();
                }
            }
        });
        mDatabase= FirebaseDatabase.getInstance().getReference().child("News");
        mDatabase.keepSynced(true);
        mNewsLists=(RecyclerView)view.findViewById(R.id.new_lists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        //mNewsLists.setHasFixedSize(true);
        mNewsLists.setItemViewCacheSize(20);
        mNewsLists.setDrawingCacheEnabled(true);
        mNewsLists.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mNewsLists.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerAdapter<News,NewsViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<News, NewsViewHolder>(News.class,
                R.layout.news_list_sample,
                NewsViewHolder.class,
                mDatabase) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, News model, int position) {
                final String post_key=getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDescription());
                viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
              Intent intent=new Intent(getActivity(),SingleNewsDetail.class);
                        intent.putExtra("news_id",post_key);
                        startActivity(intent);
                    }
                });
            }
        };
        mNewsLists.setAdapter(firebaseRecyclerAdapter);
        return view;
          }



    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public NewsViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setTitle(String title) {
            TextView post_title=(TextView)mView.findViewById(R.id.news_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_title=(TextView)mView.findViewById(R.id.news_description);
            post_title.setText(desc);
        }

        public void setImage(Context applicationContext, String image) {
            final ImageView post_image=(ImageView)mView.findViewById(R.id.news_images);
            Glide
                    .with(applicationContext)
                    .load(image)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(post_image);

        }
    }
}




