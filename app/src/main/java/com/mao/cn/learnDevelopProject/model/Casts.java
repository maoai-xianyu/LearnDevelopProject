package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class Casts implements Serializable {
    /**
     * alt : https://movie.douban.com/celebrity/1054521/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"}
     * name : 蒂姆·罗宾斯
     * id : 1054521
     */

    private String alt;
    private CastsAvators avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public CastsAvators getAvatars() {
        return avatars;
    }

    public void setAvatars(CastsAvators avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casts casts = (Casts) o;
        return Objects.equals(alt, casts.alt) &&
                Objects.equals(avatars, casts.avatars) &&
                Objects.equals(name, casts.name) &&
                Objects.equals(id, casts.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alt, avatars, name, id);
    }
}
