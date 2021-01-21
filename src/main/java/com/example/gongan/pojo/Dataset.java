package com.example.gongan.pojo;

import java.io.Serializable;
import java.util.List;

public class Dataset implements Serializable {
    private static final long serialVersionUID = 5362811471339379594L;
    private String ResourceName;
    private DataItems DataItems;
    private List<String> DataInfo;

    public Dataset() {
    }

    public Dataset(String resourceName, com.example.gongan.pojo.DataItems dataItems, List<String> dataInfo) {
        ResourceName = resourceName;
        DataItems = dataItems;
        DataInfo = dataInfo;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public void setResourceName(String resourceName) {
        ResourceName = resourceName;
    }

    public com.example.gongan.pojo.DataItems getDataItems() {
        return DataItems;
    }

    public void setDataItems(com.example.gongan.pojo.DataItems dataItems) {
        DataItems = dataItems;
    }

    public List<String> getDataInfo() {
        return DataInfo;
    }

    public void setDataInfo(List<String> dataInfo) {
        DataInfo = dataInfo;
    }
}
