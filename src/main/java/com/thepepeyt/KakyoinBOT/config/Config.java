package com.thepepeyt.KakyoinBOT.config;

import lombok.Getter;

import java.nio.file.Path;

public class Config extends xmonConfiguration<Config>{
    @Getter
    private final String token;

    public Config() {
        super("config.json", Path.of("."), Config.class);
        this.token = "token here półmuzgu wgl źle";
    }
}
