package psicon_api.service;

import psicon_api.model.Psicologo;
import psicon_api.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsicologoService {

    @Autowired
    private PsicologoRepository psicologoRepository;

    public List<Psicologo> listarActivos() {
        return psicologoRepository.findByActivoTrue();
    }

    public Psicologo obtenerPorId(Long id) {
        return psicologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psicólogo no encontrado"));
    }

    public Psicologo crear(Psicologo psicologo) {
        psicologo.setActivo(true);
        return psicologoRepository.save(psicologo);
    }

    public Psicologo actualizar(Long id, Psicologo datos) {
        Psicologo p = obtenerPorId(id);
        p.setNombre(datos.getNombre());
        p.setEspecialidad(datos.getEspecialidad());
        p.setDescripcion(datos.getDescripcion());
        p.setPrecio(datos.getPrecio());
        p.setEmail(datos.getEmail());
        p.setTelefono(datos.getTelefono());
        return psicologoRepository.save(p);
    }

    public void eliminar(Long id) {
        Psicologo p = obtenerPorId(id);
        p.setActivo(false);
        psicologoRepository.save(p);
    }
}