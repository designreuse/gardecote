package com.gardecote.web;

import com.gardecote.entities.qTaskProgressBar;

/**
 * Created by Dell on 12/04/2017.
 */
public class taskCommander {
    private qTaskProgressBar progressBar;


    public taskCommander() {
   //     this.progressBar.setStatus("created");
    }

    public qTaskProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(qTaskProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void execute() {

        this.progressBar.setStatus("executing");

        if(this.progressBar.getStatus().equals("executing"))
        this.progressBar.setProgress(100 * (this.progressBar.getNbrTaitee() + 1) / this.progressBar.getTotal());

    }


}
