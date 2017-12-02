package org.home.on.agenda;

import org.home.on.utils.GenericService;
import org.home.on.utils.ResourcePaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ResourcePaths.AGENDA_PATH)
public class ArduinoResource extends GenericService<AgendaEntity, Long> {

    @Autowired
    private Agendador agendador;

    @Override
    public AgendaEntity insert(@RequestBody AgendaEntity agenda) {
        agenda = super.insert(agenda);
        agendador.agendamento(agenda);
        return agenda;
    }

}
