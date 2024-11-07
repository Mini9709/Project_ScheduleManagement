package com.example.schedulemanagementapp.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class Paging {
    private int page;
    private int size;
    private int startpage;
    private int endpage;

    public void handlePaging(int page, int size) {
        this.page = page;
        this.size = size;
        this.startpage = (page - 1) * size;
        this.endpage = startpage + size - 1;
    }
}
