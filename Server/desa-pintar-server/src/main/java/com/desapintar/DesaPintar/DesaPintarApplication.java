package com.desapintar.DesaPintar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesaPintarApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DesaPintarApplication.class, args);
        System.out.println( "Selesai!" );
    }
}
