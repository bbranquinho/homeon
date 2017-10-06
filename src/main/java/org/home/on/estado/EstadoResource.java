package org.home.on.estado;

import org.home.on.utils.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EstadoResource extends GenericService<EstadoEntity, Long> {


}
