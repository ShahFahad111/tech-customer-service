package com.customer.exception;

public class CustomerNotFoundException extends RuntimeException {

    private final Long customerId;

    /*
     * public CustomerNotFoundException(Long id) { super("") }
     */

    public CustomerNotFoundException(Long id) {
        super("Customer Not found for id "+id);
        this.customerId=id;
    }

    public CustomerNotFoundException(String msg,Long id) {
        super(msg);
        this.customerId=id;
    }

    public CustomerNotFoundException(String msg) {
        super(msg);
        this.customerId=null;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
