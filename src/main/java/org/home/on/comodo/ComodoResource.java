package org.home.on.comodo;

import org.home.on.utils.GenericService;
import org.home.on.utils.ResourcePaths;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ResourcePaths.COMODO_PATH)
public class ComodoResource extends GenericService<ComodoEntity, Long> {


}
