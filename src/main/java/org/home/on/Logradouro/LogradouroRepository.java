package org.home.on.Logradouro;

import org.home.on.estado.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogradouroRepository extends JpaRepository<EstadoEntity, Long> {

    EstadoEntity findByCep(String cep);

    List<EstadoEntity> findByAll(String cep);

}
