package com.example.accessingdatajpa.repository;


import com.example.accessingdatajpa.modelo.EntityDatos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends CrudRepository<EntityDatos, Long> {

    List<EntityDatos> findByPrice(Integer price);
    List<EntityDatos> findByAddressContaining(String address);
    List<EntityDatos> findBySize(Integer size);

}