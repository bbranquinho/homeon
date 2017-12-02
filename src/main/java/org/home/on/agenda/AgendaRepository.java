package org.home.on.agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

    @Query("SELECT a FROM AgendaEntity as a WHERE (a.data >= :date) OR (a.repetir = true)")
    List<AgendaEntity> buscarAgendasAbertas(@Param("date") Date date);

}
