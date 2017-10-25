package org.home.on.residencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidenciaRepository extends JpaRepository<ResidenciaEntity, Long> {

    List<ResidenciaEntity> findByQtdmorador(int qtdmorador);






}
