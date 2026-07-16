package org.meeuw.svg.springboot;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@AutoConfiguration
@ConditionalOnClass(SpringTemplateEngine.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SvgObjectsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "svgObjectsTemplateResolver")
    public ITemplateResolver svgObjectsTemplateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/org/meeuw/");
        resolver.setSuffix(".svg.xml");
        resolver.setTemplateMode(TemplateMode.XML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(true);
        return resolver;
    }

    @Bean
    @ConditionalOnMissingBean(name = "svgObjectsTemplateEngine")
    public SpringTemplateEngine svgObjectsTemplateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(svgObjectsTemplateResolver());
        return engine;
    }

    @Bean
    @ConditionalOnMissingBean(SvgObjectsController.class)
    public SvgObjectsController svgObjectsController(SpringTemplateEngine svgObjectsTemplateEngine) {
        return new SvgObjectsController(svgObjectsTemplateEngine);
    }
}
