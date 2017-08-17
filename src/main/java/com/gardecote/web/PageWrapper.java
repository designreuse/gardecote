package com.gardecote.web;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lehbib on 15/05/2017.
 */
public class PageWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 20;

    private Page<T> page;

    private List<PageItem> items;

    private int currentNumber;

    private String url;
    private Date date1;
    private Date date2;
    private String searchnavire;
    private String searchusine;
    private Integer flagdetail;
    private Integer modesearch;

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getSearchnavire() {
        return searchnavire;
    }

    public void setSearchnavire(String searchnavire) {
        this.searchnavire = searchnavire;
    }

    public String getSearchusine() {
        return searchusine;
    }

    public void setSearchusine(String searchusine) {
        this.searchusine = searchusine;
    }

    public Integer getFlagdetail() {
        return flagdetail;
    }

    public void setFlagdetail(Integer flagdetail) {
        this.flagdetail = flagdetail;
    }

    public Integer getModesearch() {
        return modesearch;
    }

    public void setModesearch(Integer modesearch) {
        this.modesearch = modesearch;
    }

    public String getUrl() {

        return url;

    }

    public void setUrl(String url) {

        this.url = url;

    }

    public PageWrapper(Page<T> page){

        this.page = page;

      //  this.url = url;

        items = new ArrayList<PageItem>();

        currentNumber = page.getNumber() ; //start from 1 to match page.page

        int start, size;

        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY){

            start = 1;

            size = page.getTotalPages();

        } else {

            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY/2){

                start = 0;

                size = MAX_PAGE_ITEM_DISPLAY;

            } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY/2){

                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY ;

                size = MAX_PAGE_ITEM_DISPLAY;

            } else {

                start = currentNumber - MAX_PAGE_ITEM_DISPLAY/2;

                size = MAX_PAGE_ITEM_DISPLAY;

            }

        }

        for (int i = 0; i<size; i++){

            items.add(new PageItem(start+i, (start+i)==currentNumber));

        }

    }

    public List<PageItem> getItems(){

        return items;

    }

    public int getNumber(){

        return currentNumber;

    }

    public List<T> getContent(){

        return page.getContent();

    }



    public int getSize(){

        return page.getSize();

    }

    public int getTotalPages(){

        return page.getTotalPages();

    }

    public boolean isFirstPage(){

        return page.isFirst();

    }

    public boolean isLastPage(){

        return page.isLast();

    }

    public boolean isHasPreviousPage(){

        return page.hasPrevious();

    }

    public boolean isHasNextPage(){

        return page.hasNext();

    }

    public class PageItem {

        private int number;

        private boolean current;

        public PageItem(int number, boolean current){

            this.number = number;

            this.current = current;

        }

        public int getNumber(){

            return this.number;

        }

        public boolean isCurrent(){

            return this.current;

        }

    }

}
