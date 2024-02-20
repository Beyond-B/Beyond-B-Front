package com.example.beyond_b;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.beyond_b.book.fragment.BookFragment;
import com.example.beyond_b.chat.ChatFragment;
import com.example.beyond_b.diary.DiaryFragment;
import com.example.beyond_b.my_page.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.setItemIconTintList(null);

        transferTo(DiaryFragment.newInstance("param1", "param2"));

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.page_1) {
                    transferTo(DiaryFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_2) {
                    transferTo(ChatFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_3) {
                    transferTo(BookFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_4) {
                    transferTo(MyPageFragment.newInstance("param1", "param2"));
                    return true;
                }

                return false;
            }
        });

        navigationBarView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });

    }
    public void transferTo(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, null)
                .setReorderingAllowed(true)
                .commit();
    }
}

