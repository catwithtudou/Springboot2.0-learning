package cn.zhengyua.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author tudou
 * @date 2020/1/620:49
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(("cn.zhengyua.swaggerdemo.controller")))
                .paths(PathSelectors.any())
                .build();
    }



    /**
     * 配置信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("user manager")
                .description("user manager center 1.0")
                .termsOfServiceUrl("http://www.zhengyua.cn")
                .version("1.0")
                .contact(new Contact("Scott","http://www.zhengyua.cn","949812478@qq.com"))
                .build();
    }





}
