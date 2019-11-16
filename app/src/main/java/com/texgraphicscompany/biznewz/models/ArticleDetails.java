package com.texgraphicscompany.biznewz.models;

public class ArticleDetails {

    int id;
    String title;
    String Date;
    String source;
    String imgUrl;
    String article;
    String url;

    public ArticleDetails() {
    }

    public ArticleDetails(int id, String title, String date, String source, String imgUrl, String article, String url) {
        this.id = id;
        this.title = title;
        Date = date;
        this.source = source;
        this.imgUrl = imgUrl;
        this.article = article;
        this.url = url;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
