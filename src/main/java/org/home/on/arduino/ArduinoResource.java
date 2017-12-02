package org.home.on.arduino;

import org.home.on.utils.ResourcePaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ResourcePaths.ARDUINO_PATH)
public class ArduinoResource {

    @Autowired
    private ArduinoConector arduinoConector;

    @GetMapping
    public void conectar() {
        arduinoConector.connectar("ws://localhost:8081/websocket");
    }

}
