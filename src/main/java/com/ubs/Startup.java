package com.ubs;

import com.ubs.controller.GameController;
import com.ubs.data.HumanPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * Main class to start up the Connect4 Game
 */
@SpringBootApplication
public class Startup {

    private static final Logger log = LoggerFactory.getLogger(Startup.class);

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Startup.class);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner run(TaskExecutor executor) {
        return (args) -> {
            log.info("Initializing Game...");
            GameController gameController = applicationContext.getBean(GameController.class);
            gameController.initGame(HumanPlayer.PLAYER1, HumanPlayer.PLAYER2);
            log.info("Initializing Game......OK");
            executor.execute(gameController);
        };
    }

}
