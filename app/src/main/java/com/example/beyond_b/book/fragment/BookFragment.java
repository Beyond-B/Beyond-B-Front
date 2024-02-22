package com.example.beyond_b.book.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.beyond_b.R;
import com.example.beyond_b.book.adapter.BookListAdapter;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.databinding.FragmentBookBinding;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookFragment extends Fragment {

    private FragmentBookBinding bookBinding;
    private BookListAdapter bookListAdapter;
    private String accessToken = "";


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
        DatabaseHelper db = new DatabaseHelper(getContext());

        // 저장된 액세스 토큰 검색
        accessToken = db.getAccessToken();
        System.out.println("accessToken="+accessToken);

        bookBinding = FragmentBookBinding.inflate(inflater, container, false);
        View view = bookBinding.getRoot();

        createTabLayout();

        setupRecyclerView();

        setupTabListener();
        //처음에 fragment 띄울 시에도 api 호출 되게끔 수정.
        if (bookBinding.tabEmotions.getTabCount() > 0) {
            TabLayout.Tab defaultTab = bookBinding.tabEmotions.getTabAt(0);
            if (defaultTab != null) {
                bookBinding.tabEmotions.selectTab(defaultTab);
                //초기 탭 선택되어 색상 지정
                View tabView = defaultTab.getCustomView();
                if (tabView != null) {
                    TextView tabText = tabView.findViewById(R.id.tabText);
                    tabText.setTextColor(Color.parseColor("#86D780"));
                }
                //초기 탭 선택되어 api 호출.
                String emotion = getEmotionForTab(defaultTab.getPosition());
                fetchBooks(emotion);
            }
        }


        return view;
    }

    private void createTabLayout() {
        TabLayout tabLayout = bookBinding.tabEmotions;
        String[] tabTitles = {"HAPPY", "DEPRESSED", "ANGRY", "SURPRISED", "SAD", "WORRIED"};
        int[] tabIcons = {R.drawable.ic_happy, R.drawable.ic_depressed, R.drawable.ic_angry, R.drawable.ic_surprised, R.drawable.ic_sadness, R.drawable.ic_worried};
        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View tabView = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
            TextView tabText = tabView.findViewById(R.id.tabText);
            ImageView tabIcon = tabView.findViewById(R.id.tabIcon);;

            tabText.setText(tabTitles[i]); // 탭 제목 설정
            tabIcon.setImageResource(tabIcons[i]); // 탭 아이콘 설정
            tab.setCustomView(tabView);

            tabLayout.addTab(tab);
        }
    }

    private void setupRecyclerView() {
        bookListAdapter = new BookListAdapter(new ArrayList<>());
        bookBinding.rvBookList.setLayoutManager(new LinearLayoutManager(getContext()));
        bookBinding.rvBookList.setAdapter(bookListAdapter);

        // 아이템 클릭시 bookDetailFragment 호출
        bookListAdapter.setOnItemClickListener(book -> {
            BookDetailFragment bookDetailFragment = new BookDetailFragment();

            Bundle args = new Bundle();
            args.putInt("bookId", book.getBookId());
            args.putString("accessToken", accessToken);
            bookDetailFragment.setArguments(args);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, bookDetailFragment)
                    .addToBackStack(null) // Back stack에 추가
                    .commit();
        });
    }

    private void setupTabListener() {
        bookBinding.tabEmotions.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String emotion = getEmotionForTab(tab.getPosition());
                fetchBooks(emotion);
                View view = tab.getCustomView();
                if (view != null) {
                    TextView tabText = view.findViewById(R.id.tabText);
                    tabText.setTextColor(Color.parseColor("#86D780"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    TextView tabText = view.findViewById(R.id.tabText);
                    tabText.setTextColor(Color.parseColor("#919191"));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void fetchBooks(String emotion) {
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.BookResponse> call = apiService.getBook(emotion);
        call.enqueue(new Callback<ApiResponse.BookResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.BookResponse> call, Response<ApiResponse.BookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookListAdapter.updateBooks(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.BookResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch book details", t);
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
    }

}