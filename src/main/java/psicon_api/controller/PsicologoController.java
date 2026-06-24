package psicon_api.controller;

import psicon_api.model.Psicologo;
import psicon_api.service.PsicologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/psicologos")
@CrossOrigin(origins = "*")
public class PsicologoController {

    @Autowired
    private PsicologoService psicologoService;


    @GetMapping
    public ResponseEntity<List<Psicologo>> listar() {
        return ResponseEntity.ok(psicologoService.listarActivos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(psicologoService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Psicologo psicologo) {
        try {
            Psicologo nuevo = psicologoService.crear(psicologo);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Psicologo psicologo) {
        try {
            return ResponseEntity.ok(psicologoService.actualizar(id, psicologo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            psicologoService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Psicólogo eliminado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}