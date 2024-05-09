package org.example.domain.entity;

public interface SoftDeletable {
    boolean isDeleted();
    void setDeleted();
    void recoverDeleted();
}
