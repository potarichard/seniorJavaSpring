package hu.ponte.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class MvcConfig implements WebMvcConfigurer							// useful component, connect the built frontend static files to the spring app
{

	public void addViewControllers(ViewControllerRegistry registry)	{
		registry.addViewController("/login").setViewName("login");			// hovever no login template present in the project....
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)		// all these are for the built app, maven build will take care, that these folders, and files will be there
	{
		//static files
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

		//vue spa serve, config static resources path explicitly, set cache to 0, or No cahce,
		registry
			.addResourceHandler("/ui/**/*.css", "/ui/**/*.html", "/ui/**/*.js", "/ui/**/*.jsx", "/ui/**/*.png", "/ui/**/*.ttf", "/ui/**/*.woff", "/ui/**/*.woff2")
			.setCachePeriod(0)
			.addResourceLocations("classpath:/javascript/iq/");

		registry.addResourceHandler("/**")
			.setCachePeriod(-1)
			.addResourceLocations("classpath:/javascript/iq/index.html")
			.resourceChain(true)
			.addResolver(new PathResourceResolver() {
				@Override
				//	call localhost:8080/ui  /ui is the resource path, get resource from class path resource [javascript/iq/index.html] location for this url
				// if it exists (the resource found, then return the resource
				// if we call starts with /ui then return this resource, else ex: it starts with /asd than dont load this resource
				protected Resource getResource(String resourcePath, Resource location) throws IOException
				{
					String baseApiPath = "/ui";
					if (resourcePath.startsWith(baseApiPath) || resourcePath.startsWith(baseApiPath.substring(1))) {
						return location.exists() && location.isReadable() ? location : null;
					}

					return  null;
				}
			});
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {			// when frontend run from development server, cors call needed to be enabled
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
			.allowCredentials(true);
	}

}



