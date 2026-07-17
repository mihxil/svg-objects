package org.meeuw.svg.springboot;

import org.apache.jasper.servlet.JspServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring boot equivalent of web-fragment.xml
 */
@Configuration
public class JspServletConfig {

    @Bean
    public ServletRegistrationBean<JspServlet> spinnerServlet() {
        ServletRegistrationBean<JspServlet> bean = new ServletRegistrationBean<>(new JspServlet());
        bean.addInitParameter("jspFile", "/org/meeuw/spinner.svg.jspx");
        bean.addUrlMappings("/meeuw/spinner");
        bean.setName("svg-spinner");
        return bean;
    }

    @Bean
    public ServletRegistrationBean<JspServlet> circleServlet() {
        ServletRegistrationBean<JspServlet> bean = new ServletRegistrationBean<>(new JspServlet());
        bean.addInitParameter("jspFile", "/org/meeuw/circle.svg.jspx");
        bean.addUrlMappings("/meeuw/circle");
        bean.setName("svg-circle");
        return bean;
    }

    @Bean
    public ServletRegistrationBean<JspServlet> polygonServlet() {
        ServletRegistrationBean<JspServlet> bean = new ServletRegistrationBean<>(new JspServlet());
        bean.addInitParameter("jspFile", "/org/meeuw/polygon.svg.jspx");
        bean.addUrlMappings("/meeuw/polygon");
        bean.setName("svg-polygon");
        return bean;
    }
}
