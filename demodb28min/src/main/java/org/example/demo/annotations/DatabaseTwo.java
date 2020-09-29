package org.example.demo.annotations;

import org.apache.tapestry5.hibernate.annotations.FactoryMarker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@FactoryMarker
@Documented
public @interface DatabaseTwo
{

}
