package com.gmail.mtec.sistemas.soccernews.domain;

public class News {
    private String tittle;
    private String description;

    public News(String tittle, String description) {
        this.tittle = tittle;
        this.description = description;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
