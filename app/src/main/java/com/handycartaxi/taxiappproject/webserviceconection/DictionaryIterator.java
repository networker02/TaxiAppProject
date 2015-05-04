package com.handycartaxi.taxiappproject.webserviceconection;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Joan on 04-May-15.
 */
public class DictionaryIterator <Key,Value> implements Iterator<PairKeyValue<Key,Value>> {

    private List<PairKeyValue<Key, Value>> _values;
    private int current = -1, total;

    public DictionaryIterator(List<PairKeyValue<Key,Value>> values)
    {
        this._values = values;
        this.total = values.size();
    }

    @Override
    public boolean hasNext() {

        if (this.current == total - 1 || this.current >= total - 1)
        {
            return false;
        }

        ++current;
        return true;
    }

    @Override
    public PairKeyValue<Key, Value> next() {

        return this._values.get(current);
    }

    @Override
    public void remove() {

    }
}