/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.exception;

import java.io.Serializable;

public class DrugStoreRuntimeException extends RuntimeException implements Serializable {

    public DrugStoreRuntimeException() {
    }

    public DrugStoreRuntimeException(String message) {
        super(message);
    }

    public DrugStoreRuntimeException(Throwable t) {
        super(t);
    }

    public DrugStoreRuntimeException(String message, Throwable t) {
        super(message, t);
    }
}
