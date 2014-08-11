package com.tripadvisor.capstone.business.entity;

/**
 * @author rachana
 * @since  Aug 9, 2014
 * 
 */
public enum Status {
    TAKEN(0),
    AVAILABLE(1),
    FRIED(2);
    
    private int value;
    Status(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }

    public static Status fromValue(int value) {
        switch (value) {
            case 0: return TAKEN;
            case 1: return AVAILABLE;
            case 2: return FRIED;
            default: return TAKEN;
        }
    }
}
