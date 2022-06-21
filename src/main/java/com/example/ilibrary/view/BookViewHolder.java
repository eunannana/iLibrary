package com.example.ilibrary.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ilibrary.R;
import com.example.ilibrary.Interface.ItemClick;

public class BookViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        public TextView bookID, titleBook, ISBNBook, subjectBook, shelfBook, authorBook, publisherBook, yearBook, stockBook, descBook, summaryBook;
        public ItemClick listener;

        public BookViewHolder(@NonNull View itemView){
            super(itemView);
            bookID = (TextView) itemView.findViewById(R.id.row_ID_book);
            titleBook = (TextView) itemView.findViewById(R.id.row_title_book);
            ISBNBook = (TextView) itemView.findViewById(R.id.row_ISBN_book);
            subjectBook = (TextView) itemView.findViewById(R.id.row_subject_book);
            shelfBook = (TextView) itemView.findViewById(R.id.row_shelf_book);
            authorBook = (TextView) itemView.findViewById(R.id.row_author_book);
            publisherBook = (TextView) itemView.findViewById(R.id.row_publisher_book);
            yearBook = (TextView) itemView.findViewById(R.id.row_year_book);
            stockBook = (TextView) itemView.findViewById(R.id.row_stock_book);
            descBook = (TextView) itemView.findViewById(R.id.row_desc_book);
            summaryBook = (TextView) itemView.findViewById(R.id.row_summary_book);
        }
        public void setItemClick(ItemClick listener){
            this.listener = listener;
        }
        @Override
        public void onClick(View v){listener.onClick(v, getAdapterPosition(), false);}
    }