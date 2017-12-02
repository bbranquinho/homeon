package org.home.on.test.repositories;

import org.apache.log4j.Logger;
import org.home.on.agenda.AgendaEntity;
import org.home.on.agenda.AgendaRepository;
import org.home.on.test.utils.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class AgendaRepositoryTest extends AbstractTest {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

    @Autowired
    private AgendaRepository agendaRepository;

    @Test
    public void findAllTest() {
        List<AgendaEntity> agenda = this.agendaRepository.findAll();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Test FindAll(): " + agenda);
        }
    }

    @Test
    public void buscarAgendasAbertasTest() {
        List<AgendaEntity> agendas = this.agendaRepository.buscarAgendasAbertas(new Date());

        LOGGER.info(agendas);
    }

}
