package com.petbayo.petbayo.pager;

public class Pager {
    private int currentPage; // 현재 페이지 번호
    private int pageSize; // 페이지당 데이터 개수

    public Pager(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    // Getter 및 Setter 메서드

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
