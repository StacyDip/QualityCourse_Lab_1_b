/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.golenok.lab_1_b.presentation;

import com.golenok.lab_1_b.application_services.ApplicationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Stacy
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");
        ApplicationService appServices = (ApplicationService) context.getBean("applicationServices");
        
        appServices.delEntitiesStartNameWithK();
        appServices.delEntitiesWhereIdNoFibo();
    }
    
}
