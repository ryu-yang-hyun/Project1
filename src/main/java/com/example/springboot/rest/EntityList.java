package com.example.springboot.rest;

import java.io.Serializable;
import java.util.Collection;

public class EntityList<T> implements Serializable {
    private static final long serialVersionUID = -6307525104470545635L;
    private long totalCount;
    private Collection<T> elements;

    public EntityList(long totalCount, Collection<T> elements) {
        this.totalCount = totalCount;
        this.elements = elements;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public Collection<T> getElements() {
        return elements;
    }

    public void setElements(Collection<T> elements) {
        this.elements = elements;
    }
}
