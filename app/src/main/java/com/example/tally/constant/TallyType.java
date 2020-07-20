package com.example.tally.constant;

public enum  TallyType {
    CLOTHES(1),EAT(2),RESIDE(3),WALK(4),PLAY(5);

    public int value;

    private TallyType(int value){
        this.value = value;
    }
}
