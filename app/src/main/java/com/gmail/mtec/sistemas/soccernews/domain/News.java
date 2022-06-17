package com.gmail.mtec.sistemas.soccernews.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {

    @PrimaryKey
    public int id;

    private String tittle;
    private String description;
    private String image;
    private String link;
    public Boolean favorito ;




    public void setId(int id) {this.id = id; }

    public int getId() {return id; }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getFavorito() {return favorito;}

    public void setFavorito(Boolean favorito) {this.favorito = favorito;}
}
