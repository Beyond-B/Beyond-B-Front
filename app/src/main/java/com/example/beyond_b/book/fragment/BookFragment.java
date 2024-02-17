package com.example.beyond_b.book.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.beyond_b.book.adapter.BookListAdapter;
import com.example.beyond_b.book.model.ApiResponse;
import com.example.beyond_b.databinding.FragmentBookBinding;
import com.example.beyond_b.databinding.FragmentBookItemBinding;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFragment extends Fragment {

    private FragmentBookBinding bookBinding;
    private BookListAdapter bookListAdapter;
    private FragmentBookItemBinding itemBinding;

    public BookFragment() {

    }

    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
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
        bookBinding = FragmentBookBinding.inflate(inflater, container, false);
        View view = bookBinding.getRoot();

        setupRecyclerView();

        setupTabListener();

        return view;
    }

    private void setupRecyclerView() {
        bookListAdapter = new BookListAdapter(new ArrayList<>());
        bookBinding.rvBookList.setLayoutManager(new LinearLayoutManager(getContext()));
        bookBinding.rvBookList.setAdapter(bookListAdapter);
    }

    private void setupTabListener() {
        bookBinding.tabEmotions.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String emotion = getEmotionForTab(tab.getPosition());
                fetchBooks(emotion);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void fetchBooks(String emotion) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ApiResponse.BookResponse> call = apiService.getBook(emotion);
        call.enqueue(new Callback<ApiResponse.BookResponse>() {
            @Override
            public void onResponse(Call<ApiResponse.BookResponse> call, Response<ApiResponse.BookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookListAdapter.updateBooks(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.BookResponse> call, Throwable t) {
                System.out.println("no");
            }
        });
    }
    private String getEmotionForTab(int position) {
        switch (position) {
            case 0:
                return "HAPPY";
            case 1:
                return "DEPRESSED";
            case 2:
                return "ANGRY";
            case 3:
                return "SURPRISED";
            case 4:
                return "SADNESS";
            case 5:
                return "WORRIED";
            default:
                return "HAPPY";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bookBinding = null;
        itemBinding = null;
    }

}