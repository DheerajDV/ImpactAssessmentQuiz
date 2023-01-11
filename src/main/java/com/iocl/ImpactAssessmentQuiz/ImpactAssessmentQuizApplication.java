package com.iocl.ImpactAssessmentQuiz;

import com.iocl.ImpactAssessmentQuiz.service.MstUsageListService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ImpactAssessmentQuizApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ImpactAssessmentQuizApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ImpactAssessmentQuizApplication.class, args);
	}

}
