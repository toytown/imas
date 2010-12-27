package com.imas.common.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ValueListHandler<T> implements ValueListIterator<T> {

    protected List<T> list;
    protected ListIterator<T> listIterator;

    protected void setList(List<T> list) {
        this.list = list;
        if (list != null) {
            listIterator = list.listIterator();
        } else {
            throw new IllegalArgumentException("List empty");
        }
    }

    @Override
    public T getCurrentElement() {
        T obj = null;
        // Will not advance iterator
        if (list != null) {
            int currIndex = listIterator.nextIndex();
            obj = list.get(currIndex);
        } else {
            throw new RuntimeException();
        }

        return obj;
    }

    @Override
    public List<T> getNextElements(int count) {
        int i = 0;
        T object = null;
        LinkedList<T> list = new LinkedList<T>();
        if (listIterator != null) {
            while (listIterator.hasNext() && (i < count)) {
                object = listIterator.next();
                list.add(object);
                i++;
            }
        } // end if
        else {
            throw new RuntimeException("No data found"); // No data
        }

        return list;

    }

    @Override
    public List<T> getPreviousElements(int count) {
        int i = 0;
        T object = null;
        LinkedList<T> list = new LinkedList<T>();
        if (listIterator != null) {
            while (listIterator.hasPrevious() && (i < count)) {
                object = listIterator.previous();
                list.add(object);
                i++;
            }
        } else {
            throw new RuntimeException("No data found"); // No data
        }

        return list;

    }

    @Override
    public int getSize() {
        int size = 0;

        if (list != null) {
            size = list.size();            
        } else {
            throw new RuntimeException("No data found"); // No data
        }

        return size;
    }

    @Override
    public void resetIndex() {
        if (listIterator != null) {
            listIterator = list.listIterator();
        } else {
            throw new RuntimeException("No data found"); // No data
        }
    }
    
    @Override
    public boolean hasNext() {
    	return this.listIterator.hasNext();
    }
    
    public boolean hasPrevious() {
    	return this.listIterator.hasPrevious();
    }
}
