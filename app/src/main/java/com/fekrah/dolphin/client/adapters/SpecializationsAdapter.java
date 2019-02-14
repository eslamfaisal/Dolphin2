package com.fekrah.dolphin.client.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.BookTechnicalClientActivity;
import com.fekrah.dolphin.models.Specialize;

import java.util.List;

import static com.fekrah.dolphin.common.Constants.INAGE_CONST;

public class SpecializationsAdapter extends RecyclerView.Adapter<SpecializationsAdapter.Holder> {

    public static final String SPECIALIZE_DATA = "SPECIALIZE_DATA";

    List<Specialize> specializes ;
    Context context;

    public SpecializationsAdapter(List<Specialize> specializes, Context context) {
        this.specializes = specializes;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_specialization_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        final Specialize specialize = specializes.get(i);

        holder.name.setText(specialize.getAr_services_title());
        holder.image.setImageURI(INAGE_CONST+specialize.getServices_logo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookTechnicalClientActivity.class);
                intent.putExtra(SPECIALIZE_DATA,specialize);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return specializes.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView image;
        TextView name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}
