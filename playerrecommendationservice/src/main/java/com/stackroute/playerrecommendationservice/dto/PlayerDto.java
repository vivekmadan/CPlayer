package com.stackroute.playerrecommendationservice.dto;

public class PlayerDto {
    private String pid;
    private String name;
    private String country;
    private String battingStyle;
    private String bowlingStyle;
    private String profile;
    private String imageUrl;
    private String operation;

    public PlayerDto() {
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", battingStyle='" + battingStyle + '\'' +
                ", bowlingStyle='" + bowlingStyle + '\'' +
                ", profile='" + profile + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
