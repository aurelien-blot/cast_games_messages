package com.castruche.cast_games_messages.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class SseController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();


}
