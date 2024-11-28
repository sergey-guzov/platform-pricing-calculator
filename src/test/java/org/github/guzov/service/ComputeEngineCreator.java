package org.github.guzov.service;

import org.github.guzov.product.ComputeEngine;

public class ComputeEngineCreator {

    private static String operationSystem = "Paid: Ubuntu Pro";
    private static String provisioningModel = "Regular";
    private static String machineType = "n1-standard-8";
    private static String region = "Netherlands (europe-west4)";
    private static String localSSD = "2x375 GB";
    private static String committedTerm = "1 year";
    private static String gpuModel = "NVIDIA TESLA P100";
    private static int instancesNumber = 4;
    private static int gpuNumber = 1;

    public static ComputeEngine withSettingsFromProperty()
    {
        return new ComputeEngine(operationSystem,provisioningModel,machineType,region,localSSD,
                committedTerm,gpuModel,instancesNumber,gpuNumber);
    }

}