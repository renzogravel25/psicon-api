package psicon_api.service;

import psicon_api.dto.CitaRequest;
import psicon_api.model.Cita;
import psicon_api.model.Psicologo;
import psicon_api.model.Usuario;
import psicon_api.repository.CitaRepository;
import psicon_api.repository.PsicologoRepository;
import psicon_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public List<Cita> listarPorUsuario(Long usuarioId) {
        return citaRepository.findByUsuarioId(usuarioId);
    }

    public Cita crear(CitaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Psicologo psicologo = psicologoRepository.findById(request.getPsicologoId())
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));

        Cita cita = new Cita();
        cita.setUsuario(usuario);
        cita.setPsicologo(psicologo);
        cita.setFecha(request.getFecha());
        cita.setHora(request.getHora());
        cita.setEstado("pendiente");
        cita.setMonto(request.getMonto() != null ?
                request.getMonto() : psicologo.getPrecio());
        return citaRepository.save(cita);
    }

    public Cita actualizarEstado(Long id, String estado) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(estado);
        return citaRepository.save(cita);
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}