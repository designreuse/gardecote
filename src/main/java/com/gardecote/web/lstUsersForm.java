package com.gardecote.web;

import com.gardecote.entities.User;
import com.gardecote.entities.qLic;
import org.springframework.data.domain.Page;

/**
 * Created by lehbib on 27/06/2017.
 */
public class lstUsersForm {
    private Page<User> lstUsers;
    private int pageCount;
    private  int[] numPages;
    private int pageCourante;

    public lstUsersForm() {
    }

    public lstUsersForm(Page<User> users) {
        this.lstUsers = users;
    }

    public Page<User> getLstUsers() {
        return lstUsers;
    }

    public void setLstUsers(Page<User> lstUsers) {
        this.lstUsers = lstUsers;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int[] getNumPages() {
        return numPages;
    }

    public void setNumPages(int[] numPages) {
        this.numPages = numPages;
    }

    public int getPageCourante() {
        return pageCourante;
    }

    public void setPageCourante(int pageCourante) {
        this.pageCourante = pageCourante;
    }
}
