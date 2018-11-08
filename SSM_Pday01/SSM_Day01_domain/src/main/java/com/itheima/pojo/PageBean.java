package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {

    //当前页码：pagenum
    private Integer pageNum;
    //当前页总条数：pagesize
    private Integer pageSize;
    //总条数：totalsize
    private Integer totalSize;
    //总页数：totalpage
    private Integer totalPage;
    //当前页显示数据域：list<T>productlist
    private List<T> productList;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }


}
