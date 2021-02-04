package pl.bartekbak.lawyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.bartekbak.lawyer.dao.*;
import pl.bartekbak.lawyer.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LawyerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawyerApplication.class, args);
    }
}
