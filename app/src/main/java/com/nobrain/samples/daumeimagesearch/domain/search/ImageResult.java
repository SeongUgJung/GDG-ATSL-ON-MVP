package com.nobrain.samples.daumeimagesearch.domain.search;

import java.util.List;

public class ImageResult {
    private int result;
    private int pageCount;
    private String title;
    private int totalCount;
    private String description;
    private List<ImageItem> item;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImageItem> getItem() {
        return item;
    }

    public void setItem(List<ImageItem> item) {
        this.item = item;
    }
}
