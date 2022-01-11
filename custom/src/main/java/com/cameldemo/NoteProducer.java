/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cameldemo;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDate;

/**
 * The NoteProducer.
 */
public class NoteProducer extends DefaultProducer {
    private static final transient Log LOG = LogFactory.getLog(NoteProducer.class);
    private NoteEndpoint endpoint;


    public NoteProducer(NoteEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    CheckRange predicate=(lower,upper,value)->{
        if(value>=lower && value<=upper)
            return true;
        else
            return false;
    };

    public void process(Exchange exchange) throws Exception {
        System.out.println("Starting Note component");
        String operation=endpoint.getEndpointUri().substring(7,endpoint.getEndpointUri().indexOf('?'));


        LOG.info("Operation is "+operation);
        switch (operation){
            case "transform":transform(exchange); break;
            case "validate" :validate();break;
            default:throw new RuntimeException("Invalid operation for Note Component "+operation);
        }

    }

    private void transform(Exchange exchange){
        LOG.info("Inside Custom Note Producer---->");
        LOG.info(exchange.getIn().getBody(String.class));
        LocalDate date1=LocalDate.of(Integer.parseInt(endpoint.getYear()),Integer.parseInt(endpoint.getMonth()),
                Integer.parseInt(endpoint.getDay()));
        exchange.getIn().setHeader("CamelFileName",getFileName(date1));
        exchange.getIn().setBody("Note dated at "+date1.toString()+" : "+exchange.getIn().getBody(String.class));
        LOG.info("Body is set--------->");
    }

    public void validate(){
        boolean yearFlag=predicate.test(1000,3000,Integer.parseInt(endpoint.getYear()));
        boolean monthFlag=predicate.test(1,12,Integer.parseInt(endpoint.getMonth()));
        boolean dayFlag=predicate.test(1,31,Integer.parseInt(endpoint.getDay()));

        LOG.info("Day flag----> "+dayFlag);
        LOG.info("Month flag----> "+monthFlag);
        LOG.info("Year flag----> "+yearFlag);

    }
    private String getFileName(LocalDate date1){
        return date1.toString();
    }

    @FunctionalInterface
    public interface CheckRange{
        public boolean test(int lowerBound,int upperbound,int value);
    }
}
