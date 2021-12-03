package com.squad9.bluebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Informa para o spring não executar a classe Security que é usada por padrão
//apos a instalação no POM
@SpringBootApplication(exclude =  {SecurityAutoConfiguration.class})
public class BlueBankApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlueBankApplication.class, args);
    }


    //Utilizando um esconder disponibilizado pelo Spring
    //Colocamos a anotação Bean para indicar ao spring que esse método é um componente da aplicação
    // para usar ele na aplicação
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
