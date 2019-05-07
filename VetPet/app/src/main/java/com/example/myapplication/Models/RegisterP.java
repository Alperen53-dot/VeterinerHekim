package com.example.myapplication.Models;

public class RegisterP {
    private boolean trueFalse;
    private String text;

    public void setTrueFalse(boolean trueFalse){
        this.trueFalse = trueFalse;
    }

    public boolean isTrueFalse(){
        return trueFalse;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    @Override
    public String toString(){
        return
                "RegisterP{" +
                        "trueFalse = '" + trueFalse + '\'' +
                        ",text = '" + text + '\'' +
                        "}";
    }
}
