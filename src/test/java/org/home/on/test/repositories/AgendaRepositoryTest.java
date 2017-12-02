package org.home.on.test.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.home.on.arduino.Agenda;
import org.home.on.arduino.AgendaRepository;
import org.home.on.test.utils.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AgendaRepositoryTest extends AbstractTest{

	   private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

	    @Autowired
	    private AgendaRepository agendaRepository;

	    @Test
	    public void findAllTest() {
	        List<Agenda> agenda = this.agendaRepository.findAll();

	        if (LOGGER.isInfoEnabled()) {
	            LOGGER.info("Test FindAll(): " + agenda);
	        }
	    }



	 
	 
	 
	 
	 
	 

	 
	 
	 
	 
	 

	 

	 
	 
	 
	 

	    
}
