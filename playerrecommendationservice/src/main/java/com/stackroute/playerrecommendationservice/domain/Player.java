package com.stackroute.playerrecommendationservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Player {

    @Id
    private String pid;
    private String name;
    private String country;
    private String battingStyle;
    private String bowlingStyle;
    private String profile;
    private String imageUrl;
    private int count;

    public Player() {
    }

    public Player(String pid, String name, String country, String battingStyle, String bowlingStyle, String profile, String imageUrl, int count) {
        this.pid = pid;
        this.name = name;
        this.country = country;
        this.battingStyle = battingStyle;
        this.bowlingStyle = bowlingStyle;
        this.profile = profile;
        this.imageUrl = imageUrl;
        this.count = count;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", battingStyle='" + battingStyle + '\'' +
                ", bowlingStyle='" + bowlingStyle + '\'' +
                ", profile='" + profile + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", count=" + count +
                '}';
    }
}
