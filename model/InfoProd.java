package model;

import java.lang.annotation.*;

/**
 * Anotação para marcar metadados de classes/atributos relacionados a produtos.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface InfoProd {
    String versao() default "1.0";
    String categoria() default "Geral";
}
