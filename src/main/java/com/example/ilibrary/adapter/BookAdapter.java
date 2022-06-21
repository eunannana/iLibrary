package com.example.ilibrary.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilibrary.R;
import com.example.ilibrary.model.book;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private Context context;
    private List<book> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
    public BookAdapter(Context context, List<book> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        holder.bookCode.setText(list.get(position).getBookCode());
        holder.bookTitle.setText(list.get(position).getBookTitle());
        holder.bookSubject.setText(list.get(position).getBookSubject());
        holder.bookAuthor.setText(list.get(position).getBookAuthor());
    }
    @Override
    public int getItemCount(){
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookCode, bookTitle, bookSubject, bookAuthor;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            bookCode = itemView.findViewById(R.id.codeBook);
            bookTitle = itemView.findViewById(R.id.titleBook);
            bookSubject = itemView.findViewById(R.id.subjectBook);
            bookAuthor = itemView.findViewById(R.id.authorBook);

            itemView.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View view){
                                                if(dialog!=null){
                                                    dialog.onClick(getLayoutPosition());
                                                }
                                            }
                                        }
            );
        }
    }
}
