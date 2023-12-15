package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Ve")
public class Ve {
    @Id
    @Column(name = "ID")
    private long id;

//    @OneToMany(mappedBy = "ve", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Collection<CTVe> ctVe;

    private long status;
    public Ve() {
    }

    public Ve(long id, long status) {
        this.id = id;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ve{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
