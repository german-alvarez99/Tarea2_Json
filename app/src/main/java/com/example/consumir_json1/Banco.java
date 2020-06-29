package com.example.consumir_json1;

import com.google.gson.annotations.SerializedName;

public class Banco {
    @SerializedName("id")
    private int code;
    private String name;

    public Banco(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
