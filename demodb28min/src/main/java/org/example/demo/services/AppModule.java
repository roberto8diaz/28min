package org.example.demo.services;

import org.apache.tapestry5.hibernate.HibernateConfigurer;
import org.apache.tapestry5.hibernate.HibernateModule;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.hibernate.HibernateSessionSource;
import org.apache.tapestry5.hibernate.HibernateSymbols;
import org.apache.tapestry5.hibernate.annotations.DefaultFactory;
import org.apache.tapestry5.internal.hibernate.HibernateSessionManagerImpl;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ScopeConstants;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Marker;
import org.apache.tapestry5.ioc.annotations.Scope;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.PerthreadManager;
import org.apache.tapestry5.ioc.services.PropertyShadowBuilder;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.TapestryModule;
import org.example.demo.annotations.DatabaseTwo;
import org.hibernate.Session;

import java.lang.annotation.Annotation;

@SubModule( {HibernateModule.class, TapestryModule.class})
public class AppModule
{

    public static void bind(ServiceBinder binder)
    {
        binder.bind(DemoService.class, DemoServiceImpl.class);
    }

    @Contribute(HibernateSessionSource.class)
    public static void configureHibernateSources(OrderedConfiguration<HibernateConfigurer>
        configurers)
    {
        configurers.add("databaseOne", new HibernateConfigurer()
        {
            public void configure(org.hibernate.cfg.Configuration configuration)
            {
                configuration.configure("/databaseOne.xml");
            }

            public Class<? extends Annotation> getMarker()
            {
                return DefaultFactory.class;
            }

            public String[] getPackageNames()
            {
                return new String[] {"org.example.demo.one"};
            }
        });

        configurers.add("databaseTwo", new HibernateConfigurer()
        {
            public void configure(org.hibernate.cfg.Configuration configuration)
            {
                configuration.configure("/databaseTwo.xml");
            }

            public Class<? extends Annotation> getMarker()
            {
                return DatabaseTwo.class;
            }

            public String[] getPackageNames()
            {
                return new String[] {"org.example.demo.two"};
            }
        });
    }

    @Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public static void addSymbols(MappedConfiguration<String, String> configuration)
    {
        configuration.add(HibernateSymbols.DEFAULT_CONFIGURATION, "false");
        configuration.add("tapestry.app-package", "org.example.demo");
    }


    @Scope(ScopeConstants.PERTHREAD)
    @Marker(DatabaseTwo.class)
    public static HibernateSessionManager buildHibernateSessionManagerForFinacle(
        HibernateSessionSource sessionSource,
        PerthreadManager perthreadManager)
    {
        HibernateSessionManagerImpl service = new HibernateSessionManagerImpl(sessionSource,
            DatabaseTwo.class);

        perthreadManager.addThreadCleanupListener(service);

        return service;
    }

    @Marker(DatabaseTwo.class)
    public static Session buildSessionForFinacle(
        @Local HibernateSessionManager
            sessionManager,
        PropertyShadowBuilder propertyShadowBuilder)
    {
        return propertyShadowBuilder.build(sessionManager, "session", Session.class);
    }

}
