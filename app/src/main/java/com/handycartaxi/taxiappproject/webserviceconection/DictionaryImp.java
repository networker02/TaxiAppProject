package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public  class DictionaryImp<Key,Value> extends Dictionary<Key,Value> implements Iterable<PairKeyValue<Key, Value>> {


    private ArrayList<PairKeyValue<Key,Value>> data = new ArrayList();


    @Override
    public Enumeration<Value> elements() {

        List<Value> values = new ArrayList();
        for (int i = 0, total = this.data.size();i<this.data.size();i++)
        {
            values.add(this.data.get(i).getValue());
        }

        return Collections.enumeration(values);
    }

    @Override
    public Value get(Object key) {

        if(key instanceof Integer)
            return this.data.get((Integer)key).getValue();


        Key _key = (Key)key;
        for (int i = 0, total = this.data.size(); i < total; i++)
        {
            if (this.data.get(0).getKey().equals(_key) || data.get(0).getKey() == _key)
                return this.data.get(i).getValue();
        }


        return null;
    }


    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public Enumeration<Key> keys() {

        List<Key> keys = new ArrayList();
        for (PairKeyValue<Key,Value> k : this.data)
            keys.add(k.getKey());

        return Collections.enumeration(keys);
    }

    @Override
    public Value put(Key key, Value value) {
        data.add(new PairKeyValue<Key, Value>(key,value));

        return value;

    }

    @Override
    public Value remove(Object key) {

        Key _key = (Key)key;
        for(int i =0,total = this.data.size(); i < total;i++)
        {
            if (this.data.get(i).getKey().equals(_key))
            {
                return this.data.remove(i).getValue();
            }
        }

        return null;
    }


    @Override
    public int size() {
        return this.data.size();
    }


    @Override
    public Iterator<PairKeyValue<Key, Value>> iterator() {
        return new DictionaryIterator(this.data);
    }}