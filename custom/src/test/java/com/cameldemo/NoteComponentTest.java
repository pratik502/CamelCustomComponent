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

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class NoteComponentTest extends CamelTestSupport {

    @Produce(uri = "direct:startOne")
    protected ProducerTemplate templateOne;

    @EndpointInject(uri = "mock:resultOne")
    protected MockEndpoint resultEndpointOne;

    @Produce(uri = "direct:startTwo")
    protected ProducerTemplate templateTwo;

    @EndpointInject(uri = "mock:resultTwo")
    protected MockEndpoint resultEndpointTwo;

    @Produce(uri = "direct:startThree")
    protected ProducerTemplate templateThree;

    @EndpointInject(uri = "mock:resultThree")
    protected MockEndpoint resultEndpointThree;

    @Produce(uri = "direct:startFour")
    protected ProducerTemplate templateFour;

    @EndpointInject(uri = "mock:resultFour")
    protected MockEndpoint resultEndpointFour;

    @Test
    public void testTimerInvokesBeanMethod() throws Exception {
        resultEndpointOne.expectedMessageCount(1);
        resultEndpointOne.expectedBodiesReceived("Note dated at 2022-01-11 : I like idali");
        templateOne.sendBody("I like idali");
        resultEndpointOne.assertIsSatisfied();
    }

    @Test
    public void testTimerInvokesBeanMethod2() throws Exception {
        resultEndpointTwo.expectedMessageCount(2);
        resultEndpointTwo.expectedBodiesReceived("Note dated at 2022-01-12 : I played FIFA mobile and Royal match","Note dated at 2022-01-12 : I played Angry Birds dream blast");
        templateTwo.sendBody("I played FIFA mobile and Royal match");
        templateTwo.sendBody("I played Angry Birds dream blast");
        resultEndpointTwo.assertIsSatisfied();
    }

    @Test
    public void validateEndpointTest() throws Exception{
        resultEndpointThree.expectedMessageCount(1);
        resultEndpointThree.expectedBodiesReceived("I like pizza");
        templateThree.sendBody("I like pizza");
        resultEndpointThree.assertIsSatisfied();
    }

    @Test
    public void validateEndpointTestNegative() throws Exception{
        resultEndpointFour.expectedMessageCount(1);
        resultEndpointFour.expectedBodiesReceived("I like pizza");
        templateThree.sendBody("I like pizza");
        resultEndpointFour.assertIsNotSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                context.addComponent("note",new NoteComponent());
                from("direct:startOne").to("note://transform?day=11&month=1&year=2022").to("mock:resultOne");
                from("direct:startTwo").to("note://transform?day=12&month=1&year=2022").to("mock:resultTwo");
                from("direct:startThree").to("note://validate?day=12&month=1&year=2022").to("mock:resultThree");
                from("direct:startFour").to("note://validate?day=35&month=1&year=2022").to("mock:resultFour");
            }
        };
    }
}
