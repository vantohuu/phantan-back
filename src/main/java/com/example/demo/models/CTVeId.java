package com.example.demo.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class CTVeId implements Serializable {
    @Id
    @Column(name = "username")
    private String username;
    @Id
    @Column(name = "idve")
    private Long IDVE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CTVeId ctVeId = (CTVeId) o;
        return Objects.equals(username, ctVeId.username) && Objects.equals(IDVE, ctVeId.IDVE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, IDVE);
    }
}
