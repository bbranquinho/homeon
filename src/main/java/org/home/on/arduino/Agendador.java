package org.home.on.arduino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Agendador {

    @Autowired
    private ArduinoConector arduinoConector;

    private List<Agenda> agendamentos = new LinkedList();

    private Timer timer = new Timer();

    public void agendamento(Long id, String hostname, String comando, Date data, boolean repetir) {
        adicionarAgenda(new Agenda(id, hostname, comando, data, repetir));
    }

    public void cancelarAgendamento(Long id) {
        agendamentos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .ifPresent(a -> agendamentos.remove(a));
    }

    private void novoAgendamento(Agenda agenda) {
        if (agenda.isRepetir()) {
            Calendar c = Calendar.getInstance();
            c.setTime(agenda.getData());
            c.add(Calendar.DATE, 1);

            Agenda novaAgenda = new Agenda(agenda.getId(), agenda.getHostname(), agenda.getComando(), c.getTime(), agenda.isRepetir());
            adicionarAgenda(novaAgenda);
        } else {
            Agenda primeiroAgendamento = agendamentos.remove(0);
            timer.schedule(new Tarefa(primeiroAgendamento), primeiroAgendamento.getData());
        }
    }

    private void adicionarAgenda(Agenda agenda) {
        timer.cancel();
        agendamentos.add(agenda);
        agendamentos.stream().sorted(Comparator.comparing(Agenda::getData));

        Agenda primeiroAgendamento = agendamentos.remove(0);
        timer.schedule(new Tarefa(primeiroAgendamento), primeiroAgendamento.getData());
    }

    private class Tarefa extends TimerTask {

        private Agenda agenda;

        public Tarefa(Agenda agenda) {
            this.agenda = agenda;
        }

        @Override
        public void run() {
            arduinoConector.enviarMensagem(agenda.getHostname(), agenda.getComando());
            novoAgendamento(agenda);
        }
    }

}
