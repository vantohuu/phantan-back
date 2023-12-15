package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@IdClass(CTVeId.class)
public class CTVe {
    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "idve")
    private Long IDVE;
//    @ManyToOne
//    @JoinColumn(name = "username")
//    @JsonIgnore
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "idve")
//    @JsonIgnore
//    private Ve ve;

    private Long status;

    private Date create_at;

    public CTVe(){

    }

    public CTVe(String username, Long IDVE, Long status, Date create_at) {
        this.username = username;
        this.IDVE = IDVE;
        this.status = status;
        this.create_at = create_at;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

//    public Ve getVe() {
//        return ve;
//    }

//    public void setVe(Ve ve) {
//        this.ve = ve;
//    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

//    public User getUser() {
//        return user;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIDVE() {
        return IDVE;
    }

    public void setIDVE(Long IDVE) {
        this.IDVE = IDVE;
    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "CTVe{" +
                "username='" + username + '\'' +
                ", IDVE=" + IDVE +
                ", status=" + status +
                ", create_at=" + create_at +
                '}';
    }
}
