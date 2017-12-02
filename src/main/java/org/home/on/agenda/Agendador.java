package org.home.on.agenda;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Agendador {

    private static final Logger LOGGER = LogManager.getLogger(Agendador.class);

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ArduinoConector arduinoConector;

    private List<AgendaEntity> agendamentos = new LinkedList();

    private Timer timer = new Timer();

    public void agendamento(AgendaEntity agenda) {
        adicionarAgenda(agenda);
    }

    public void agendamento(Long id, String hostname, String comando, Date data, boolean repetir) {
        adicionarAgenda(new AgendaEntity(id, hostname, comando, data, repetir));
    }

    private void novoAgendamento(AgendaEntity agenda) {
        if (agenda.isRepetir()) {
            Calendar c = Calendar.getInstance();
            c.setTime(agenda.getData());
            c.add(Calendar.DATE, 1);
            agenda.setData(c.getTime());

            agendaRepository.save(agenda);

            adicionarAgenda(agenda);
        } else {
            AgendaEntity primeiroAgendamento = agendamentos.remove(0);
            timer.schedule(new Tarefa(primeiroAgendamento), primeiroAgendamento.getData());
        }
    }

    private void adicionarAgenda(AgendaEntity agenda) {
        LOGGER.info("Agendamento realizado para " + agenda);

        agendamentos.add(agenda);
        agendamentos.stream().sorted(Comparator.comparing(AgendaEntity::getData));

        AgendaEntity primeiroAgendamento = agendamentos.remove(0);
        timer.schedule(new Tarefa(primeiroAgendamento), primeiroAgendamento.getData());
    }

    private class Tarefa extends TimerTask {

        private AgendaEntity agenda;

        public Tarefa(AgendaEntity agenda) {
            this.agenda = agenda;
        }

        @Override
        public void run() {
            LOGGER.info("Enviando comando da agenda " + agenda);

            arduinoConector.enviarMensagem(agenda.getHostname(), agenda.getComando());
            novoAgendamento(agenda);
        }
    }

}
