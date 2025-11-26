package model;

import java.lang.annotation.*;

// Anotação @InfoProd, usada em Produto e RepositorioHash
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface InfoProd {
    String versao() default "1.0";
    String categoria() default "Geral";
}
