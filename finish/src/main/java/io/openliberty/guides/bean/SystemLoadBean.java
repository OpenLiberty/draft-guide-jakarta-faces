// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.bean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import com.sun.management.OperatingSystemMXBean;

import io.openliberty.guides.bean.model.SystemLoadData;

// tag::namedAnnotation[]
@Named("systemLoadBean")
// end::namedAnnotation[]
// tag::applicationScopedAnnotation[]
@ApplicationScoped
// end::applicationScopedAnnotation[]
// tag::systemLoadBean[]
public class SystemLoadBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SystemLoadData> systemLoads;

    private static final OperatingSystemMXBean OS =
        (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private static final MemoryMXBean MEM =
        ManagementFactory.getMemoryMXBean();

    // tag::postConstruct[]
    @PostConstruct
    // end::postConstruct[]
    // tag::init[]
    public void init() {
        systemLoads = new ArrayList<>();
        fetchSystemLoad();
    }
    // end::init[]

    // tag::fetchSystemLoadMethod[]
    public void fetchSystemLoad() {
        String time = Calendar.getInstance().getTime().toString();

        double cpuLoad = OS.getCpuLoad() * 100;

        long heapMax = MEM.getHeapMemoryUsage().getMax();
        long heapUsed = MEM.getHeapMemoryUsage().getUsed();
        double memoryUsage = heapUsed * 100.0 / heapMax;

        SystemLoadData data = new SystemLoadData(time, cpuLoad, memoryUsage);

        systemLoads.add(data);
    }
    // end::fetchSystemLoadMethod[]

    // tag::getSystemLoads[]
    public List<SystemLoadData> getSystemLoads() {
        return systemLoads;
    }
    // end::getSystemLoads[]
}
// end::systemLoadBean[]
