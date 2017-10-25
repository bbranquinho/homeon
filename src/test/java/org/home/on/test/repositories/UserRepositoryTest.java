package org.home.on.test.repositories;

import org.apache.log4j.Logger;
import org.home.on.test.utils.AbstractTest;
import org.home.on.user.UserEntity;
import org.home.on.user.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest extends AbstractTest {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        List<UserEntity> users = this.userRepository.findAll();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Test FindAll(): " + users);
        }
    }



    @Test
    public void addTest() {
        String name = "Usuario Teste";
        String email = "caio" + System.currentTimeMillis() + "@user.com";
        String password = "1234";

        UserEntity usuario = new UserEntity();
        usuario.setName(name);
        usuario.setEmail(email);
        usuario.setPassword(password);

        usuario = this.userRepository.save(usuario);

        // LOGGER.info("Test Add usuario: "+ usuario);
        assertNotNull(usuario);
        assertTrue(usuario.getId() != null);
        assertThat(usuario.getName()).isEqualTo(name);

    }

    @Test
    public void updateTest() {
        if (this.userRepository.findByName("Usuario Teste").size() <= 0) {
            addTest();
        }

        UserEntity usuarioFind = this.userRepository.findByName("Usuario Teste").get(0);

		/* Test Update */

        //assertNotNull(usuarioFind);

        usuarioFind.setName("Usuario Teste Update");
        LOGGER.info("Test update usuario" + this.userRepository.save(usuarioFind));

        assertNotNull(usuarioFind);
        assertTrue(usuarioFind.getId() != null);
        assertThat(usuarioFind.getName()).isEqualTo("Usuario Teste Update");

    }



}
