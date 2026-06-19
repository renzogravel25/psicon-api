package psicon_api.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequest {
    private Long usuarioId;
    private Long psicologoId;
    private LocalDate fecha;
    private LocalTime hora;
    private Double monto;
}