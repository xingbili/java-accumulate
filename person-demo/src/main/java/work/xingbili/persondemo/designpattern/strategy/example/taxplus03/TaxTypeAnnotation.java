package work.xingbili.persondemo.designpattern.strategy.example.taxplus03;

import work.xingbili.persondemo.designpattern.strategy.example.tax.TaxType;

import java.lang.annotation.*;

/**
 * @author xinghuolin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TaxTypeAnnotation {
    TaxType taxType();
}