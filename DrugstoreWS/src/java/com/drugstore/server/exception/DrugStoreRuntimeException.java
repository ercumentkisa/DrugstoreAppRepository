package com.drugstore.server.exception;

import java.io.Serializable;

public class DrugStoreRuntimeException extends RuntimeException implements Serializable{
	
	public DrugStoreRuntimeException(){
		
	}
	
	public DrugStoreRuntimeException(String message){
		super(message);
	}
	
	public DrugStoreRuntimeException(Throwable t){
		super(t);
	}
	
	public DrugStoreRuntimeException(String message, Throwable t){
		super(message, t);
	}
}
