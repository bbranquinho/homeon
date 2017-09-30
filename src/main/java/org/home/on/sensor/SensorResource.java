package org.home.on.sensor;

import org.home.on.utils.GenericService;
import org.home.on.utils.ResourcePaths;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ResourcePaths.SENSOR_PATH)
public class SensorResource extends GenericService<SensorEntity, Long> {


}
