package org.example.demo.two;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EntityTwo
{
    @Id
    @GeneratedValue
    private Long id;
    
    private String foo;
    
    private String bar;

    public Long getId()
    {
        
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFoo()
    {
        return foo;
    }

    public void setFoo(String foo)
    {
        this.foo = foo;
    }

    public String getBar()
    {
        return bar;
    }

    public void setBar(String bar)
    {
        this.bar = bar;
    }
}
