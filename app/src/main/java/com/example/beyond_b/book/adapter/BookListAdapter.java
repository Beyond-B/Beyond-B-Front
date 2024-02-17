package com.example.beyond_b.book.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.beyond_b.book.model.Book;
import com.example.beyond_b.databinding.FragmentBookBinding;
import com.example.beyond_b.databinding.FragmentBookItemBinding;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private List<Book> bookList;

    public BookListAdapter(List bookList){
        this.bookList = bookList;
    }

    public void updateBooks(List<Book> newBooks) {
        bookList.clear();
        bookList.addAll(newBooks);
        notifyDataSetChanged(); // 데이터가 변경되었음을 알리는 코드
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        FragmentBookItemBinding binding = FragmentBookItemBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Book item = bookList.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private FragmentBookItemBinding itemBinding;
        private FragmentBookBinding bookBinding;

        public ViewHolder(FragmentBookItemBinding itemBinding ) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        //여기서 바인딩. api의 변수들.
        public void bind(Book item){
            itemBinding.txTitle.setText(item.getTitle());
            itemBinding.txAuthor.setText(item.getAuthor());
            itemBinding.icBadge.setImageResource(item.getBadgeResource());
        }
    }

}

