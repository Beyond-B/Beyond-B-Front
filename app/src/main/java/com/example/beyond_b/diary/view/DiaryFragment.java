package com.example.beyond_b.diary.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beyond_b.R;
import com.example.beyond_b.databinding.FragmentDiaryBinding;
import com.example.beyond_b.diary.adapter.CalendarViewHolder;
import com.example.beyond_b.diary.adapter.CalendarAdapter;
import com.example.beyond_b.diary.model.DiaryCalendar;
import com.example.beyond_b.diary.view.write.firstWriteActivity;
import com.example.beyond_b.membership.DatabaseHelper;
import com.example.beyond_b.network.ApiResponse;
import com.example.beyond_b.network.ApiService;
import com.example.beyond_b.network.RetrofitClient;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DiaryFragment extends Fragment implements CalendarAdapter.onItemListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private String accessToken = "";
    private FragmentDiaryBinding binding;
    private TextView monthText, yearText;
    private RecyclerView calenderRecyclerView;
    private LocalDate selectedDate;
    private final ArrayList<Integer> diaryIds = new ArrayList<>();

    private int diaryId;

    public DiaryFragment() {
        // Required empty public constructor
    }
    public static DiaryFragment newInstance(String param1, String param2) {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentDiaryBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        DatabaseHelper db = new DatabaseHelper(getContext());
        accessToken = db.getAccessToken();

        //글쓰기 버튼 클릭시
        binding.diaryWriteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), firstWriteActivity.class));
            }
        });

        //달력 지난 달
        binding.diaryCalendarPreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonthAction(rootView);
            }
        });

        //달력 다음 달
        binding.diaryCalendarNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonthAction(rootView);
            }
        });

        initiWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthText.setText(monthFromDate(selectedDate));
        yearText.setText(yearFromDate(selectedDate));

        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        Log.d("size", String.valueOf(daysInMonth.size()));
        for (int i = 0; i < daysInMonth.size(); i++) {
            diaryIds.add(i,0); // 초기값으로 0
        }

        //한달 일기 불러오기
        fetchMonthlyDiary();

        Log.d("daryId", String.valueOf(diaryIds));

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, diaryIds, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireActivity(), 7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth  = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i=1; i<=42; i++){
            if(i<=dayOfWeek || i>daysInMonth+dayOfWeek){
                daysInMonthArray.add("");
            }
            else{
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthFromDate(LocalDate date){
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.KOREAN);; //로케일 사용해서 한국 달력으로 변환
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(formatter);
        }
        return null;
    }


    private String monthFromDateNum(LocalDate date){
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MM").withLocale(Locale.KOREAN);;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(formatter);
        }
        return null;
    }


    private String yearFromDate(LocalDate date){
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(formatter);
        }
        return null;
    }

    private void initiWidgets() {
        calenderRecyclerView = binding.diaryCalendarRecyclerview;
        monthText = binding.diaryMonth;
        yearText = binding.diaryYear;
    }

    public void previousMonthAction(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.minusMonths(1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setMonthView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }


    //날짜 클릭시 이벤트
    @Override
    public void onItemClick(int position, int diaryId, String dayText, ImageView moodImg) {
        //감정일기 작성해서 감정이모지 보일 시 작성한 일기 보여줌
        if(!dayText.equals("") && moodImg.getVisibility() == View.VISIBLE){
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, CalendarCellClickFragment.newInstance(diaryId))
                    .addToBackStack(null)
                    .commit();
        }
        else{

        }
    }


    //한달간 일기 조회
    private void fetchMonthlyDiary(){
        Retrofit retrofit = RetrofitClient.getClient(accessToken);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse.MonthlyDiaryResponse> call = apiService.monthlyDiary(yearFromDate(selectedDate) ,monthFromDateNum(selectedDate));
        call.enqueue(new Callback<ApiResponse.MonthlyDiaryResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse.MonthlyDiaryResponse> call, Response<ApiResponse.MonthlyDiaryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //날짜 받아오기
                    ArrayList<String> daysInMonth = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        daysInMonth = daysInMonthArray(selectedDate);
                    }

                    //일기 list 불러오기
                    ApiResponse.MonthlyDiaryResponse monthlyDiaryResponse = response.body();
                    List<DiaryCalendar> list = monthlyDiaryResponse.getResult().getDiarySummaries();

                    //존재하는 일기 list만큼 반복하여 맞는 날짜의 posion에 감정 이모지 띄움
                    for (DiaryCalendar summary : list) {
                        String date = summary.getDate();
                        String day = date.substring(date.lastIndexOf('-') + 1);

                        //diaryId 저장
                        int dayIndex = Integer.parseInt(day) - 1;
                        diaryIds.set(dayIndex, summary.getDiaryId());

                        if (daysInMonth.contains(day)) {
                            int index = daysInMonth.indexOf(day);
                            RecyclerView.ViewHolder viewHolder = calenderRecyclerView.findViewHolderForAdapterPosition(index);
                            if (viewHolder instanceof CalendarViewHolder) {
                                CalendarViewHolder calendarViewHolder = (CalendarViewHolder) viewHolder;
                                calendarViewHolder.setDiaryId(summary.getDiaryId());
                                //감정별 경우의 수
                                if (summary.getFeeling().equals("HAPPY")) {
                                    calendarViewHolder.moodImg.setVisibility(View.VISIBLE);
                                    calendarViewHolder.moodImg.setImageResource(R.drawable.ic_happy);
                                } else if (summary.getFeeling().equals("DEPRESSED")) {
                                    calendarViewHolder.moodImg.setVisibility(View.VISIBLE);
                                    calendarViewHolder.moodImg.setImageResource(R.drawable.ic_depressed);
                                } else if (summary.getFeeling().equals("SURPRISED")) {
                                    calendarViewHolder.moodImg.setVisibility(View.VISIBLE);
                                    calendarViewHolder.moodImg.setImageResource(R.drawable.ic_surprised);
                                } else if (summary.getFeeling().equals("ANGRY")) {
                                    calendarViewHolder.moodImg.setVisibility(View.VISIBLE);
                                    calendarViewHolder.moodImg.setImageResource(R.drawable.ic_angry);
                                } else if (summary.getFeeling().equals("SADNESS")) {
                                    calendarViewHolder.moodImg.setVisibility(View.VISIBLE);
                                    calendarViewHolder.moodImg.setImageResource(R.drawable.ic_sadness);
                                } else if (summary.getFeeling().equals("WORRIED")) {
                                    calendarViewHolder.moodImg.setVisibility(View.VISIBLE);
                                    calendarViewHolder.moodImg.setImageResource(R.drawable.ic_worried);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.MonthlyDiaryResponse> call, Throwable t) {
                Log.e("API Error", "Failed to fetch book details", t);
            }
        });
    }
}