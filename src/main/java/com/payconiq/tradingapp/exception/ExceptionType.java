package com.payconiq.tradingapp.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    LOCKED("stock.locked"),
    ID_UNAVAILABLE("unavailable"),
    UPDATE_FAILED_EXCEPTION("exception");
    
    String value;
    
    ExceptionType(String value) {
        this.value = value;
    }
    
    String getValue() {
        return this.value;
    }
}
