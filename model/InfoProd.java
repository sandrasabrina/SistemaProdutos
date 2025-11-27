package model;

import java.lang.annotation.*;

/**
 * Anotação utilizada para fornecer metadados a classes ou atributos de produtos.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface InfoProd {
    String versao() default "1.0";           // Versão do produto ou classe
    String categoria() default "Geral";      // Categoria padrão
}
