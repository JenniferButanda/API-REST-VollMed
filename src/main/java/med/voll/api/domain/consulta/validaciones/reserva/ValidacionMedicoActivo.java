package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservaConsulta datos) {
        if(datos.idMedico() == null) {
            return;
        }
        var medicoActivo = repository.findActivoByID(datos.idMedico());
        if(!medicoActivo) {
            throw new ValidacionException("No es posible agendar una cita debido a que el médico se encuentra inactivo");
        }
    }
}
