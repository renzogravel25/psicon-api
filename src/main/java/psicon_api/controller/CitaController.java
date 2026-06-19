package psicon_api.controller;

import psicon_api.dto.CitaRequest;
import psicon_api.model.Cita;
import psicon_api.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // GET - Listar todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> listar() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

    // GET - Listar citas por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Cita>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(citaService.listarPorUsuario(usuarioId));
    }

    // POST - Crear nueva cita
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CitaRequest request) {
        try {
            Cita cita = citaService.crear(request);
            return ResponseEntity.ok(cita);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // PUT - Actualizar estado de cita
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id,
                                              @RequestBody Map<String, String> body) {
        try {
            String estado = body.get("estado");
            Cita cita = citaService.actualizarEstado(id, estado);
            return ResponseEntity.ok(cita);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE - Eliminar cita
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            citaService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Cita eliminada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}