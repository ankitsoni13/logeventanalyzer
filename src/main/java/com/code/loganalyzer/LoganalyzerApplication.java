package com.code.loganalyzer;

import com.code.loganalyzer.processor.LogEntryProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

@Slf4j
@EnableCaching
@SpringBootApplication
public class LoganalyzerApplication {

	public static void main(String[] args) {
		if(args == null || args.length<1 || !new File(args[0]).isFile()){
			throw new RuntimeException("File not Found at provided location");
		}

		String fileLocation = args[0];
		log.info("File Path {}",fileLocation);

		ConfigurableApplicationContext ctx = SpringApplication.run(LoganalyzerApplication.class, args);
		LogEntryProcessor logEntryProcessor = ctx.getBean(LogEntryProcessor.class);
		log.info("Process Start -------------->");
		logEntryProcessor.processLogFile(fileLocation);
		log.info("<---------------Process End");
	}

}
