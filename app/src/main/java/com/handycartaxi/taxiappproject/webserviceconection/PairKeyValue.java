package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */
public class PairKeyValue<Key,Value>  {
    private Key key;
    private Value value;

    public PairKeyValue(){}

    public PairKeyValue(Key key,Value value)
    {
        setKey(key);
        setValue(value);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }




}
