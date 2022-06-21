package com.example.ilibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilibrary.R;
import com.example.ilibrary.model.Reservation;
import com.example.ilibrary.model.book;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    private Context context;
    private List<book> list;
    private BookAdapter.Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(BookAdapter.Dialog dialog){
        this.dialog = dialog;
    }
    public ReservationAdapter(Context context, List<reservation> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reservation, parent, false);
        return new ReservationAdapter.MyViewHolder(itemView);
    }

}
