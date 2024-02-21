package com.example.beyond_b.my_page;

import com.google.gson.annotations.SerializedName;

public class MyPage {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("picture")
    private String picture;
    @SerializedName("age")
    private String age;

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getUsername(){
        return this.username;
    }
}
