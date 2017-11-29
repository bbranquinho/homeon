package org.home.on.dispositivo;

import org.home.on.utils.GenericService;
import org.home.on.utils.ResourcePaths;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ResourcePaths.DISPOSITIVO_PATH)
public class DispositivoResource extends GenericService<DispositivoEntity, Long> {


}
