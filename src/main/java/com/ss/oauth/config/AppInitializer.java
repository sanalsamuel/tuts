package com.ss.oauth.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
 

  
    
    
    @Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.ss.oauth");
		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic spring = container.addServlet("api", new DispatcherServlet(context));
		spring.setInitParameter("contextClass", context.getClass().getName());
		container.addListener(new ContextLoaderListener(context));
		container.addListener(new HttpSessionEventPublisher());
		container.addFilter("filter", new AppFilter());
		spring.setLoadOnStartup(1);
		spring.addMapping("/*");

	}
    
 
}