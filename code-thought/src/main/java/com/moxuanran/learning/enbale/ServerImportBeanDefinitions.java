package com.moxuanran.learning.enbale;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/25 下午7:52
 */
public class ServerImportBeanDefinitions implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ImportSelector importSelector = new ServerImportSelector();
        String[] selectClassNames = importSelector.selectImports(importingClassMetadata);
        Stream.of(selectClassNames)
                .map(BeanDefinitionBuilder::genericBeanDefinition)
                .map(BeanDefinitionBuilder::getBeanDefinition)
                .forEach(b ->
                        BeanDefinitionReaderUtils.registerWithGeneratedName(b, registry)
                );

    }
}
