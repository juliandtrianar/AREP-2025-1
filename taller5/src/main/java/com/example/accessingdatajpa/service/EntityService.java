package com.example.accessingdatajpa.service;


import com.example.accessingdatajpa.repository.EntityRepository;
import com.example.accessingdatajpa.modelo.EntityDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;

    public List<EntityDatos> obtenerdatos(){
        return (List<EntityDatos>) entityRepository.findAll();
    }



    public EntityDatos AgregarEntidad (EntityDatos entidad ){
        return entityRepository.save(entidad);
    }


    public void eliminarEntidad(Long id) {
        entityRepository.deleteById(id);

    }

    public EntityDatos obtenerPorId(Long id) {
        return entityRepository.findById(id).orElse(null);
    }


    public List<EntityDatos> findByAddress(String address) {
        return entityRepository.findByAddressContaining(address);
    }

    public List<EntityDatos> findBySize(Integer size) {
        return entityRepository.findBySize(size);
    }

    public List<EntityDatos> findByPrice(Integer price) {
        return entityRepository.findByPrice(price);
    }


}
