package com.example.beyond_b;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.beyond_b.R;
import com.example.beyond_b.book.fragment.BookFragment;
import com.example.beyond_b.diary.view.DiaryFragment;
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
                    transferTo(BookFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_3) {
                    transferTo(MyPageFragment.newInstance("param1", "param2"));
                    return true;
                }

                return false;
            }
        });

        navigationBarView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                // Do nothing on reselect
            }
        });

        applyCustomFontToMenuItems(navigationBarView);
    }

    private void applyCustomFontToMenuItems(BottomNavigationView bottomNavigationView) {
        Typeface customFont = ResourcesCompat.getFont(this, R.font.ownglyph_ryuttung); // your_custom_font.ttf 파일을 res/font 디렉토리에 추가

        // BottomNavigationView의 모든 메뉴 아이템에 대해 순회
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem item = bottomNavigationView.getMenu().getItem(i);

            // 각 메뉴 아이템의 ActionView를 가져옴
            bottomNavigationView.post(() -> {
                View view = bottomNavigationView.findViewById(item.getItemId());
                if (view != null) {
                    // smallLabel과 largeLabel을 찾아서 폰트 적용
                    TextView smallLabel = view.findViewById(com.google.android.material.R.id.navigation_bar_item_small_label_view);
                    TextView largeLabel = view.findViewById(com.google.android.material.R.id.navigation_bar_item_large_label_view);
                    if (smallLabel != null) {
                        smallLabel.setTypeface(customFont);
                    }
                    if (largeLabel != null) {
                        largeLabel.setTypeface(customFont);
                    }
                }
            });
        }
    }

    public void transferTo(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, null)
                .setReorderingAllowed(true)
                .commit();
    }

    public void showBottomNavigation(boolean show) {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
