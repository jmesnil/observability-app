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

import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.opentracing.Traced;


@Stateless
@Traced
public class HelloService {

    @Inject
    @ConfigProperty(name="app.hello", defaultValue = "Hello")
    private String hello;

    public String createHelloMessage(String name) {

        try {
            Thread.sleep((long)(new Random()).nextInt(20));
        } catch (InterruptedException e) {
        }

        if ((new Random().nextInt(10) <= 3)) {
            throw new IllegalStateException("Big problem in my application");
        }

        return hello + " " + name + "!";
    }

}
