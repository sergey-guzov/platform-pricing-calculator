package org.github.guzov.service;

import org.github.guzov.product.ComputeEngine;

public class ComputeEngineCreator {

    private static final String OPERATION_SYSTEM = "testdata.operation.system";
    private static final String PROVISIONING_MODEL = "testdata.provisioning.model";
    private static final String MACHINE_TYPE = "testdata.machine.type";
    private static final String REGION = "testdata.region";
    private static final String LOCAL_SSD = "testdata.local.ssd";
    private static final String COMMITTED_TERM = "testdata.committed.term";
    private static final String GPU_MODEL = "testdata.gpu.model";
    private static final String INSTANCE_NUMBER = "testdata.instance.number";
    private static final String GPU_NUMBER = "testdata.gpu.number";

    public static ComputeEngine withSettingsFromProperty()
    {
        return new ComputeEngine(TestDataReader.getTestData(OPERATION_SYSTEM),
                TestDataReader.getTestData(PROVISIONING_MODEL),
                TestDataReader.getTestData(MACHINE_TYPE),
                TestDataReader.getTestData(REGION),
                TestDataReader.getTestData(LOCAL_SSD),
                TestDataReader.getTestData(COMMITTED_TERM),
                TestDataReader.getTestData(GPU_MODEL),
                TestDataReader.getTestData(INSTANCE_NUMBER),
                TestDataReader.getTestData(GPU_NUMBER));
    }
}