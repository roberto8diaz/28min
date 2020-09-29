package org.example.demo.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.hibernate.annotations.DefaultFactory;
import org.example.demo.annotations.DatabaseTwo;
import org.example.demo.one.EntityOne;
import org.example.demo.two.EntityTwo;

import java.util.List;

public interface DemoService
{
    List<EntityOne> listOnes();

    List<EntityTwo> listTwos();

    @CommitAfter
    @DefaultFactory
    void save(EntityOne entityOne);

    @CommitAfter
    @DatabaseTwo
    void save(EntityTwo entityTwo);
}
