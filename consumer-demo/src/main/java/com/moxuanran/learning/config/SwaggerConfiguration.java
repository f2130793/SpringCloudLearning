package com.moxuanran.learning.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 莫轩然
 * @date 2022/7/21 14:58
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {
    //todo 该模块应该提供一个公共组件，方便各个服务配置swagger接口文档，需统一管理可以使用yapi
    /** 是否开启swagger */
    private boolean enabled = false;
    /** controller类所在包 */
    private String controllerPackage = "com.moxuanran.learning.controller";
    /** 文档标题 */
    private String title = "连信demo服务";
    /** 文档描述 */
    private String description;
    /** 服务条款url */
    private String termsOfServiceUrl;
    /** 联系人 */
    private String contact;
    /** 接口文档版本 */
    private String version;

    @Bean
    public Docket createRestApi() {
        if (controllerPackage == null) {
            throw new RuntimeException("You enabled swagger2, but have not assign the controllerPackage!");
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(controllerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title != null ? title : "未指定文档标题")
                .description(description != null ? description : "-")
                .termsOfServiceUrl(termsOfServiceUrl != null ? termsOfServiceUrl : "-")
                .contact(contact != null ? contact : "-")
                .version(version != null ? version : "1.0")
                .build();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SwaggerConfiguration{" +
                "enabled=" + enabled +
                ", controllerPackage='" + controllerPackage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", contact='" + contact + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
