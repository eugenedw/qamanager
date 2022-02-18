package com.grplife.qamanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class QAManagerApp 
{
    public static void main( String[] args )
    {
		try{
			SpringApplication.run(QAManagerApp.class, args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
}
