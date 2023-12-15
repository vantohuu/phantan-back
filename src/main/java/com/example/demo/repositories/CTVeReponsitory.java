package com.example.demo.repositories;

import com.example.demo.models.CTVe;
import com.example.demo.models.CTVeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface CTVeReponsitory extends JpaRepository<CTVe, CTVeId> {
    @Query(value = "select id as idve, ve.status, username, create_at from ve \n" +
            "left join ctve\n" +
            "on ve.id = ctve.idve\n" +
            "and ctve.create_at = (select max(create_at) from ctve where ve.id = ctve.idve)\n" +
            "order by ve.id", nativeQuery = true)
    List<CTVe> getAllOrderVe();
}
