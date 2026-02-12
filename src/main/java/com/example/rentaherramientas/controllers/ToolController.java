package com.example.rentaherramientas.controllers;

import com.example.rentaherramientas.models.Tool;
import com.example.rentaherramientas.models.User;
import com.example.rentaherramientas.repositories.ToolRepository;
import com.example.rentaherramientas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    // --- CREAR HERRAMIENTA ---
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createTool(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("pricePerDay") Double pricePerDay,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam("file") MultipartFile file) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User provider = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Tool tool = new Tool();
            tool.setName(name);
            tool.setDescription(description);
            tool.setPricePerDay(BigDecimal.valueOf(pricePerDay));
            tool.setProvider(provider);
            tool.setStock(stock != null ? stock : 1);
            tool.setStatus("DISPONIBLE");

            saveFile(tool, file);

            return ResponseEntity.ok(toolRepository.save(tool));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear: " + e.getMessage());
        }
    }

    // --- ACTUALIZAR HERRAMIENTA (EDITAR) ---
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateTool(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("pricePerDay") Double pricePerDay,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Tool tool = toolRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));

            tool.setName(name);
            tool.setDescription(description);
            tool.setPricePerDay(BigDecimal.valueOf(pricePerDay));
            if (stock != null) tool.setStock(stock);

            // Solo actualiza la imagen si se sube un archivo nuevo
            if (file != null && !file.isEmpty()) {
                saveFile(tool, file);
            }

            return ResponseEntity.ok(toolRepository.save(tool));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    // --- ELIMINAR HERRAMIENTA ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTool(@PathVariable Long id) {
        try {
            Tool tool = toolRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));

            toolRepository.delete(tool);
            return ResponseEntity.ok(Map.of("message", "Herramienta eliminada con éxito"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar: " + e.getMessage());
        }
    }

    // Método privado para reutilizar la lógica de guardado de archivos
    private void saveFile(Tool tool, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            tool.setImageUrl("/uploads/" + fileName);
        }
    }
}