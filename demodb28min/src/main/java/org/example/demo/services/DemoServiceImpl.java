package org.example.demo.services;

import org.apache.tapestry5.hibernate.annotations.DefaultFactory;
import org.example.demo.annotations.DatabaseTwo;
import org.example.demo.one.EntityOne;
import org.example.demo.two.EntityTwo;
import org.hibernate.Session;

import java.util.List;

public class DemoServiceImpl implements DemoService
{
    private Session sessionOne;

    private Session sessionTwo;

    public DemoServiceImpl(
        @DefaultFactory Session sessionOne,
        @DatabaseTwo Session sessionTwo)
    {
        this.sessionOne = sessionOne;
        this.sessionTwo = sessionTwo;
    }

    @SuppressWarnings("unchecked")
    public List<EntityOne> listOnes()
    {
        return sessionOne.createCriteria(EntityOne.class).list();
    }

    @SuppressWarnings("unchecked")
    public List<EntityTwo> listTwos()
    {
        return sessionTwo.createCriteria(EntityTwo.class).list();
    }

    public void save(EntityOne entityOne)
    {
        sessionOne.saveOrUpdate(entityOne);
    }

    public void save(EntityTwo entityTwo)
    {
        sessionTwo.saveOrUpdate(entityTwo);
    }
}
