package com.cameldemo.rabbitmq;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CustomProcessor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(CustomProcessor.class);

    public CustomProcessor() {
        System.out.println("CustomProcessor Created..");
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        logger.info("Inside processor");
        System.out.println(exchange.getIn().getBody());
        List<Student> studentList=(List<Student>)exchange.getIn().getBody();

        for(Student stud:studentList)
            stud.getAndSetCalculatedPercentage();

        logger.info("Printing modified list");
        logger.info("<=====================================================>");
        //System.out.println(studentList);
        logger.info("<=====================================================>");
        exchange.getIn().setBody(studentList);
    }
}

