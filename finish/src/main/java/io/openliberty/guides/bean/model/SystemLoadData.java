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
package io.openliberty.guides.bean.model;

public class SystemLoadData {
    private String time;
    private Double cpuLoad;
    private Double memoryUsage;

    public SystemLoadData(String time, Double cpuLoad, Double memoryUsage) {
        this.time = time;
        this.cpuLoad = cpuLoad;
        this.memoryUsage = memoryUsage;
    }

    public String getTime() {
        return time;
    }

    public Double getCpuLoad() {
        return cpuLoad;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }
}
