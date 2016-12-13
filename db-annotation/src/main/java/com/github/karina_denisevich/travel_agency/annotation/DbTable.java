package com.github.karina_denisevich.travel_agency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the primary table for the annotated entity.
 *
 * If no <code>Table</code> annotation is specified for an entity
 * class, the default values apply.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTable {

    String name() default "";
}
