package com.stackroute.favouriteservice.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Player {
    @Id
    private String pid;
    private String name;
    private String country;
    private String battingStyle;
    private String bowlingStyle;
    private String profile;
    private String imageUrl;

    public Player() {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(pid, player.pid) &&
                Objects.equals(name, player.name) &&
                Objects.equals(country, player.country) &&
                Objects.equals(battingStyle, player.battingStyle) &&
                Objects.equals(bowlingStyle, player.bowlingStyle) &&
                Objects.equals(profile, player.profile) &&
                Objects.equals(imageUrl, player.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, name, country, battingStyle, bowlingStyle, profile, imageUrl);
    }
}
