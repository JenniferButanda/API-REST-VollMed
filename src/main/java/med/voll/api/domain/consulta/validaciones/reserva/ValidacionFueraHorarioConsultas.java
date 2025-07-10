package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacionFueraHorarioConsultas implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioUltimaHoraConsulta = fechaConsulta.getHour() > 18;
        if(domingo || horarioAntesDeAperturaClinica || horarioUltimaHoraConsulta) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atención.");
        }
    }
}
