package com.example.beyond_b.book.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.beyond_b.databinding.FragmentBookDetailBinding;

public class BookDetailFragment extends Fragment {

    private FragmentBookDetailBinding bookDetailBinding;
    private int bookId;

    public BookDetailFragment() {
        // Required empty public constructor
    }

    public static BookDetailFragment newInstance(int bookId) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putInt("bookId", bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bookDetailBinding = FragmentBookDetailBinding.inflate(inflater, container, false);
        View view = bookDetailBinding.getRoot();
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            bookId = getArguments().getInt("bookId");
            fetchBookDetail(bookId);
        }

        return view;

    }

    private void fetchBookDetail(int bookId) {

    }
}