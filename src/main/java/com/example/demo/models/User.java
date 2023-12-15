package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.Collection;

@Entity
@Table(name = "Users")
public class User {
    @Id
    private String username;
    private String password;
    private String ten;
    private  String ho;
    private  String diachi;

    private String avatar;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Collection<CTVe> ctVe;

    public User() {
    }

    public User(String username, String password, String ten, String ho, String diachi, String avatar) {
        this.username = username;
        this.password = password;
        this.ten = ten;
        this.ho = ho;
        this.diachi = diachi;
        this.avatar = avatar;
    }
//    public User(String username, String password, String ten, String ho, String diachi, String avatar, Collection<CTVe> ctVe) {
//        this.username = username;
//        this.password = password;
//        this.ten = ten;
//        this.ho = ho;
//        this.diachi = diachi;
//        this.avatar = avatar;
//        this.ctVe = ctVe;
//    }


//    public Collection<CTVe> getCtVe() {
//        return ctVe;
//    }
//
//    public void setCtVe(Collection<CTVe> ctVe) {
//        this.ctVe = ctVe;
//    }
//


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ten='" + ten + '\'' +
                ", ho='" + ho + '\'' +
                ", diachi='" + diachi + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
