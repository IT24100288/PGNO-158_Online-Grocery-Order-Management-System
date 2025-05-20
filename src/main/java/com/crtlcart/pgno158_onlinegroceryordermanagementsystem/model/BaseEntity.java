package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Abstract method that all entities must implement
    public abstract String getDisplayName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 