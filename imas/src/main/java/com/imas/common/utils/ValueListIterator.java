package com.imas.common.utils;

import java.util.List;

public interface ValueListIterator<T> {

    public int getSize();

    public T getCurrentElement();

    public List<T> getPreviousElements(int count);

    public List<T> getNextElements(int count);

    public void resetIndex();

    public boolean hasNext() ;
    
    
}
