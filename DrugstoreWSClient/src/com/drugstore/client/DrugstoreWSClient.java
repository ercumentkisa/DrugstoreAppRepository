/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client;

import com.drugstore.client.ws.DrugstoreWS_Service;

/**
 *
 * @author ekisa
 */
public class DrugstoreWSClient {
    private static DrugstoreWSClient INSTANCE;

    private static DrugstoreWS_Service webService;
    
    private DrugstoreWSClient() {
        webService = new DrugstoreWS_Service();
    }
    
    public static synchronized DrugstoreWSClient getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DrugstoreWSClient();
        }
        
        return INSTANCE;
    }

    public DrugstoreWS_Service getWebService() {
        return webService;
    }
}
