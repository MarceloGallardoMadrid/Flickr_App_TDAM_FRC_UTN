package com.nahue.flickrapp.Entidades;

public class Comment {
    private Long id;
    private String authorname;
    private Long datecreate;
    private String realname;
    private String _content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public Long getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Long datecreate) {
        this.datecreate = datecreate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }


}
