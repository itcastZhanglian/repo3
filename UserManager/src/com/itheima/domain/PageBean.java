package com.itheima.domain;

import jdk.nashorn.internal.ir.WithNode;

import java.util.List;

/**
 * 封装分页数据的javaBean类
 * @param <T>
 */
public class PageBean<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页码数
    private List<T> list;//每页查询的数据集合
    private int currentPage;//当前页
    private int rows;//每页显示的记录数
    private int startPage;//起始页码
    private int endPage;//结尾页码

    public PageBean() {
    }

    public PageBean(int totalCount, int totalPage, List<T> list, int currentPage, int rows, int startPage, int endPage) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        //总页数小于10页,则完整显示页数
        if(totalPage<=10){
            endPage=totalPage;
            startPage=1;
        }else{
            //前5后4
            startPage=currentPage-5;
            endPage=currentPage+4;
            //前5页的情况
            if(startPage<1){
                startPage=1;
                endPage=10;
            }
            //最后几页的情况
            if (endPage>totalPage){
                endPage=totalPage;
                startPage=totalPage-9;
            }
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                '}';
    }
}
