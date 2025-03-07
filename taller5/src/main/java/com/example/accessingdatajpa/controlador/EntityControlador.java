package com.example.accessingdatajpa.controlador;

import com.example.accessingdatajpa.modelo.EntityDatos;
import com.example.accessingdatajpa.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EntityControlador {

    @Autowired
    private EntityService entityService;
    @GetMapping("/test")
    public String test() {
        return "Hello, World!";
    }
    @GetMapping("/agregar") // Este método maneja la solicitud GET
    public String mostrarFormulario(Model model) {
        model.addAttribute("entityDatos", new EntityDatos());
        return "formulario"; // Nombre del archivo HTML
    }

    @PostMapping("/agregar") // Este método maneja la solicitud POST
    public String agregarEntidad(EntityDatos entidad) {
        entityService.AgregarEntidad(entidad);
        return "redirect:/agregar"; // Redirige después de agregar
    }

    @GetMapping("/api/todos")
    @ResponseBody
    public List<EntityDatos> obtenerEntidadesJson() {
        return entityService.obtenerdatos();
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEntidad(@PathVariable Long id) {
        entityService.eliminarEntidad(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EntityDatos> actualizarEntidad(@PathVariable Long id, @RequestBody EntityDatos entidad) {
        EntityDatos entidadExistente = entityService.obtenerPorId(id);

        // Actualiza los campos de la entidad existente
        entidadExistente.setAddress(entidad.getAddress());
        entidadExistente.setSize(entidad.getSize());
        entidadExistente.setPrice(entidad.getPrice());
        entidadExistente.setDescription(entidad.getDescription());
        entityService.AgregarEntidad(entidadExistente); // Guarda la entidad actualizada
        return ResponseEntity.ok(entidadExistente);
    }
    @GetMapping("/api/entidades")
    @ResponseBody
    public List<EntityDatos> obtenerEntidades(@RequestParam(required = false) String address,
                                              @RequestParam(required = false) Integer size,
                                              @RequestParam(required = false) Integer price) {
        if (address != null) {
            return entityService.findByAddress(address);
        } else if (size != null) {
            return entityService.findBySize(size);
        } else if (price != null) {
            return entityService.findByPrice(price);
        }
        return entityService.obtenerdatos();
    }


}





