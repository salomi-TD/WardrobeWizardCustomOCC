/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training;

/**
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
public class AppConfig
{
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // Set maximum file size
		return resolver;
	}
}

