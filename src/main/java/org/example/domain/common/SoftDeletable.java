package org.example.domain.common;

public interface SoftDeletable {
    boolean isDeleted();
    void setDeleted();
    void recoverDeleted();
}
