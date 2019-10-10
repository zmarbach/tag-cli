package com.improving.tagcli.models;

public class Emote {
    private String prompt;
    private String text;

    public Emote(String prompt, String text) {
        this.prompt = prompt;
        this.text = text;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getText() {
        return text;
    }
}

