/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.jmesnil;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.opentracing.Traced;

@Path("/")
@Traced
public class Endpoints {

    @Inject
    HelloService helloService;

    @GET
    @Path("/json")
    @Produces({ "application/json" })
    @Counted(description = "Counter for JSON endpoint", absolute = true, monotonic = true)
    public String getHelloWorldJSON(@DefaultValue("World") @QueryParam("name") String name) {
        return "{\"result\":\"" + helloService.createHelloMessage(name) + "\"}";
    }

    @GET
    @Path("/xml")
    @Produces({ "application/xml" })
    @Counted(description = "Counter for XML endpoint", absolute = true, monotonic = true)
    public String getHelloWorldXML(@DefaultValue("World") @QueryParam("name") String name) {
        return "<xml><result>" + helloService.createHelloMessage(name) + "</result></xml>";
    }

}
