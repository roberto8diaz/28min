package org.example.demo.services;

import org.apache.tapestry5.ioc.IOCUtilities;
import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.tapestry5.test.TapestryTestCase;
import org.example.demo.one.EntityOne;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoServiceImplTest extends TapestryTestCase
{
    private Registry registry;
    
    private DemoService service;

    @BeforeClass
    public void setup()
    {
        RegistryBuilder registryBuilder = new RegistryBuilder();
        
        registryBuilder.add(AppModule.class);

        IOCUtilities.addDefaultModules(registryBuilder);
        
        registry = registryBuilder.build();

        registry.performRegistryStartup();

        service = registry.getService(DemoService.class);
    }

    @AfterClass
    public void shutdown()
    {
        if(registry != null)
        {
            registry.shutdown();
        }
    }
    
    
    @Test
    public void testListOnes()
    {

        EntityOne entityOne = new EntityOne();
        entityOne.setBar("Bar1");
        entityOne.setFoo("Foo1");
        service.save(entityOne);
        
        assertEquals(service.listOnes().size(), 1);
        
        EntityOne e = service.listOnes().get(0);
        
        assertEquals(e.getBar(), entityOne.getBar());
        assertEquals(e.getFoo(), entityOne.getFoo());
    }
}
