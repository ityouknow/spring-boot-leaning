package com.neo;


import org.springframework.stereotype.Component;

@Component
public class SourceWrapper {
    private String name;
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("SourceWrapper[name='%s', text='%s']",
                this.name, this.text);
    }
}
