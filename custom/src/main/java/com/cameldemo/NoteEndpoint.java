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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Represents a HelloWorld endpoint.
 */
public class NoteEndpoint extends DefaultEndpoint {

    private String day;
    private String month;
    private String year;
    private String validate;

    private Map parameters;
    private String endpointUri;
    private String remaining;

    public NoteEndpoint() {
    }

    private static final transient Log LOG = LogFactory.getLog(NoteEndpoint.class);

    public NoteEndpoint(String uri, NoteComponent component) {
        super(uri, component);
        this.endpointUri=uri;
        LOG.info("1 Note URI "+this.endpointUri);
    }

    public NoteEndpoint(String endpointUri) {
        super(endpointUri);
        this.endpointUri=endpointUri;
        LOG.info("2 Note URI "+this.endpointUri);
    }

    public NoteEndpoint(String endpointUri, String remaining, Map<String, Object> parameters){
        this.endpointUri=endpointUri;
        this.remaining=remaining;
        this.parameters=parameters;

        LOG.info("--------------------------------------->");
        LOG.info("Note URI "+this.endpointUri);
        LOG.info("Note remaining "+this.remaining);
        LOG.info("Note parameters "+this.parameters);
        LOG.info("--------------------------------------->");
    }

    public Producer createProducer() throws Exception {
        return new NoteProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new NoteConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }
}
