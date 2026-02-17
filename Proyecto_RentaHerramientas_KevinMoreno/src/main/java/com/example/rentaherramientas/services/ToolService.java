package com.example.rentaherramientas.services;

import com.example.rentaherramientas.models.Tool;
import com.example.rentaherramientas.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public List<Tool> listarTodas() {
        return toolRepository.findAll();
    }

    public Tool guardar(Tool tool) {
        return toolRepository.save(tool);
    }

    public Optional<Tool> buscarPorId(Long id) {
        return toolRepository.findById(id);
    }

    public void eliminar(Long id) {
        toolRepository.deleteById(id);
    }
}