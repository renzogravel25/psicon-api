package psicon_api.repository;

import psicon_api.model.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
    List<Psicologo> findByActivoTrue();
}