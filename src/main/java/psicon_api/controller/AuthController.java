package psicon_api.controller;

import psicon_api.dto.LoginRequest;
import psicon_api.dto.LoginResponse;
import psicon_api.dto.UsuarioRequest;
import psicon_api.model.Usuario;
import psicon_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody UsuarioRequest request) {
        try {
            Usuario usuario = authService.registrar(request);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Usuario registrado correctamente",
                    "id", usuario.getId(),
                    "email", usuario.getEmail(),
                    "rol", usuario.getRol()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok(Map.of(
                "ok", true,
                "mensaje", "API Psicon funcionando",
                "version", "1.0.0"
        ));
    }
}