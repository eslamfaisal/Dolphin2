package com.fekrah.dolphin.client.adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.FuckenResponse;
import com.fekrah.dolphin.models.Notification;
import com.fekrah.dolphin.server.BaseClient;
import com.rafakob.drawme.DrawMeButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.Holder> {


    List<Notification> notifications;
    Context context;

    public NotificationsAdapter(List<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_notification_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        Notification notification = notifications.get(i);
        holder.date.setText(notification.getDate_notification());
        if (notification.getOrder_status().equals("1")) {
            holder.desc.setText(context.getString(R.string.confirm_reservation));
            holder.actionView.setVisibility(View.VISIBLE);
            holder.arrival.setVisibility(View.VISIBLE);
        }else if (notification.getOrder_status().equals("4")){
            holder.desc.setText(context.getString(R.string.technical_choosed));
            holder.arrival.setText(notification.getTechnical_arrival());
            holder.actionView.setVisibility(View.GONE);
        }

        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmOrder(1,notification.getId_order());
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder(3,notification.getId_order());
            }
        });

        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder(1,notification.getId_order());
            }
        });
        holder.delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(notification.getId_order());

            }
        });
    }

    public int getItemCount() {
        return notifications.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        DrawMeButton confirm, cancel, delay;
        TextView date, desc ,arrival;
        View actionView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            arrival = itemView.findViewById(R.id.technical_arrival);
            actionView = itemView.findViewById(R.id.action_view);
            confirm = itemView.findViewById(R.id.confirm);
            cancel = itemView.findViewById(R.id.cancel);
            delay = itemView.findViewById(R.id.delay);
            date = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.desc);

        }
    }

    void confirmOrder(int type,String orderId){
        BaseClient.getApi().confirmOrder(SharedHelper.getKey(context, RegisterActivity.USER_ID),orderId,type)
                .enqueue(new Callback<FuckenResponse>() {
                    @Override
                    public void onResponse(Call<FuckenResponse> call, Response<FuckenResponse> response) {
                        if (response.body().getSuccess_confirm()==1)
                            Toast.makeText(context, "تم التاكيد", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(context, "لم تم التاكيد", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FuckenResponse> call, Throwable t) {

                    }
                });
    }

    void orderAnotherDate(int type,String orderId,String date){

        BaseClient.getApi().orderAnotherDate(SharedHelper.getKey(context, RegisterActivity.USER_ID),orderId,type,date)
                .enqueue(new Callback<FuckenResponse>() {
                    @Override
                    public void onResponse(Call<FuckenResponse> call, Response<FuckenResponse> response) {
                        if (response.body().getSuccess_confirm()==1)
                            Toast.makeText(context, "تم التجيل", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(context, "لم تم التجيل", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FuckenResponse> call, Throwable t) {

                    }
                });
    }

    void showDatePicker(String orderId){
        Calendar newCalendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");

                // Toast.makeText(getApplicationContext(), ""+format.format(new java.util.Date(year, monthOfYear, dayOfMonth)), Toast.LENGTH_SHORT).show();
                orderAnotherDate(2,orderId,format.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }
}
