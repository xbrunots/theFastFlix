package com.devbruno.fastshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bsilvabr on 12/02/2018.
 */

public class Genres implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
