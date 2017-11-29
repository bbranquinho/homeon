package org.home.on.residencia;

import org.home.on.utils.GenericService;
import org.home.on.utils.ResourcePaths;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ResourcePaths.RESIDENCIA_PATH)
public class ResidenciaResource extends GenericService<ResidenciaEntity, Long> {


}
