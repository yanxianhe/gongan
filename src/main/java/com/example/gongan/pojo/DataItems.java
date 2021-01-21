package com.example.gongan.pojo;

import java.io.Serializable;

public class DataItems implements Serializable {
    private static final long serialVersionUID = -8925412857633996287L;
    private String Name;
    private String Type;
    private String Fmt;

    public DataItems() {
    }

    public DataItems(String name, String type, String fmt) {
        Name = name;
        Type = type;
        Fmt = fmt;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFmt() {
        return Fmt;
    }

    public void setFmt(String fmt) {
        Fmt = fmt;
    }

}
