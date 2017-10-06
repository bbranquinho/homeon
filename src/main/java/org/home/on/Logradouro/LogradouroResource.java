package org.home.on.Logradouro;

import org.home.on.utils.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LogradouroResource extends GenericService<LogradouroEntity, Long> {


}
