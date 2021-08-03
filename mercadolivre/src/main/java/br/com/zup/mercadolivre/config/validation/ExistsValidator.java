package br.com.zup.mercadolivre.config.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsValidator implements ConstraintValidator<Exists, Long> {
	
	private String domainAttribute;
	
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
 
    @Override
    public void initialize(Exists params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }
 
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
    	if(klass.getName() == "Categoria" && domainAttribute == "categoriaMae" && value == null) {
    		return true;
    	}
    	Query query = manager.createQuery("select 1 from " +klass.getName()+" where " +domainAttribute +"=:value");
    	query.setParameter("value", value);
    	List<?> list = query.getResultList();
    	return !(list.isEmpty());
    }
}