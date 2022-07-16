package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
public class InfoController {
    private final ServerProperties serverProperties;
    Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${serverPort}")
    private int serverPort;

    public InfoController(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @GetMapping("/getPort")
    public ResponseEntity<Integer> getPortNumber() {
        int port = serverProperties.getPort();
        return ResponseEntity.ok(port);
    }

    @GetMapping("/getSum")
    public ResponseEntity<Integer> getSum() {
        long time = System.currentTimeMillis();
        logger.debug("Start summing");
        int sum = IntStream.rangeClosed(1,1000000).parallel().reduce(0, Integer::sum);
        time = System.currentTimeMillis() - time;
        logger.debug("Summing takes, time{}", time);
        return ResponseEntity.ok(sum);
    }

}
