package com.fekrah.dolphin.client.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.models.OrderResponse;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

import static com.fekrah.dolphin.common.Constants.INAGE_CONST;

public class NewOrdersAdapters extends RecyclerView.Adapter <NewOrdersAdapters.Holder>{

    List<OrderResponse >orders ;
    Context context;

    public NewOrdersAdapters(List<OrderResponse> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_finished_orders_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        OrderResponse order= orders.get(i);

        holder.address.setText(order.getOrder_address());
        holder.date.setText(order.getOrder_date());
        holder.image.setImageURI(INAGE_CONST+order.getOrder_image());

//        PrettyTime p = new PrettyTime();
//
//        long time = Long.parseLong(order.getOrder_date_str());
//
//        holder.from_time.setText(p.format(new Date(time)));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        SimpleDraweeView image;
        TextView name,address,date,from_time,hours;
        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.profile_image);
            address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.date);
            from_time = itemView.findViewById(R.id.from_tim);
            hours = itemView.findViewById(R.id.hours);
        }
    }
}
