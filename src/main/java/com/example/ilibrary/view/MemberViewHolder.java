package com.example.ilibrary.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilibrary.R;
import com.example.ilibrary.Interface.ItemClick;

public class MemberViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
    public TextView textMemberID, textMemberName, textMemberPhone;
    public ItemClick listener;

    public MemberViewHolder(@NonNull View itemView){
        super(itemView);
        textMemberID = (TextView)  itemView.findViewById(R.id.row_ID_member);
        textMemberName = (TextView) itemView.findViewById(R.id.row_name_member);
        textMemberPhone = (TextView) itemView.findViewById(R.id.row_phone_member);
    }
    public void setItemClick(ItemClick listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v){listener.onClick(v, getAdapterPosition(), false);}
}