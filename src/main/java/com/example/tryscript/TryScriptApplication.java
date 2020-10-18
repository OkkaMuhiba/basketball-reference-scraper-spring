package com.example.tryscript;

import com.example.tryscript.command.scrap.GetAllPlayersPerSeasonsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TryScriptApplication implements CommandLineRunner {

    @Autowired
    GetAllPlayersPerSeasonsCommand command;

    public static void main(String[] args) {
        SpringApplication.run(TryScriptApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        command.run("2020");
    }
}
