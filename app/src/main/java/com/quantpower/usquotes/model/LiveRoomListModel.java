package com.quantpower.usquotes.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/3.
 */

public class LiveRoomListModel implements Serializable {


    /**
     * id : 1
     * speaker_name : ???
     * speaker_img : http://118.178.121.211//Public/attached/2016/12/30/5865c5dd14afd.jpg
     * speaker_description : ???????
     * speaker_title : ?
     * speaker_look : 0
     * speaker_state : 0
     */

    private int id;
    private String speaker_name;
    private String speaker_img;
    private String speaker_description;
    private String speaker_description1;
    private String speaker_title;
    private String speaker_look;
    private String speaker_state;
    private int room_id;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getSpeaker_popularity() {
        return speaker_popularity;
    }

    public void setSpeaker_popularity(String speaker_popularity) {
        this.speaker_popularity = speaker_popularity;
    }

    private String speaker_popularity;

    public String getSpeaker_description1() {
        return speaker_description1;
    }

    public void setSpeaker_description1(String speaker_description1) {
        this.speaker_description1 = speaker_description1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeaker_name() {
        return speaker_name;
    }

    public void setSpeaker_name(String speaker_name) {
        this.speaker_name = speaker_name;
    }

    public String getSpeaker_img() {
        return speaker_img;
    }

    public void setSpeaker_img(String speaker_img) {
        this.speaker_img = speaker_img;
    }

    public String getSpeaker_description() {
        return speaker_description;
    }

    public void setSpeaker_description(String speaker_description) {
        this.speaker_description = speaker_description;
    }

    public String getSpeaker_title() {
        return speaker_title;
    }

    public void setSpeaker_title(String speaker_title) {
        this.speaker_title = speaker_title;
    }

    public String getSpeaker_look() {
        return speaker_look;
    }

    public void setSpeaker_look(String speaker_look) {
        this.speaker_look = speaker_look;
    }

    public String getSpeaker_state() {
        return speaker_state;
    }

    public void setSpeaker_state(String speaker_state) {
        this.speaker_state = speaker_state;
    }
}
