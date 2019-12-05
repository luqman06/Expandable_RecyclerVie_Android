package com.example.expandablerecyclerviewandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    private List<Hero> heroList;
    private Context context;


    private static int currentPosition = 0;


public HeroAdapter(List<Hero> heroList, Context context){
    this.heroList = heroList;
    this.context = context;

}

    @NonNull
    @Override
    public HeroAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_heroes,parent, false);

        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.HeroViewHolder holder, final int position) {

    Hero hero = heroList.get(position);
    holder.textViewName.setText(hero.getName());
    holder.textViewRealName.setText(hero.getRealName());
    holder.textViewTeam.setText(hero.getTeam());
    holder.textViewFirstAppearance.setText(hero.getFirstAppearance());
    holder.textViewCreatedBy.setText(hero.getCreatedBy());
    holder.textViewPublisher.setText(hero.getPublisher());
    holder.textViewBio.setText(hero.getBio());

        Glide.with(context).load(hero.getImageUrl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);



// jika posisinya sama dengan posisi item yang akan diperluas
        if(currentPosition == position){

            //membuat animasi

            Animation slideDown = AnimationUtils.loadAnimation(context,R.anim.anim);

// mengubah visibilitas
            holder.linearLayout.setVisibility(View.VISIBLE);


// menambahkan efek geser
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


// dapatkan posisi item untuk memperluasnya
                currentPosition = position;


// memuat ulang daftar
                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
        textViewCreatedBy, textViewPublisher, textViewBio;

    ImageView imageView;
    LinearLayout linearLayout;

    HeroViewHolder(View itemView){
        super(itemView);

        textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
        textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
        textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
        textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
        textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
        textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

        linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

    }
    }
}
