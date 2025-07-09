package com.tiendahm.util;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendahmappBackendApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima")); // 🔥 Aquí se fuerza la zona
        SpringApplication.run(TiendahmappBackendApplication.class, args);
    }
}
