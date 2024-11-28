package org.github.guzov.product;

public class ComputeEngine extends AbstractProduct {
    private final String PRODUCT_NAME;
    private String operationSystem;
    private String provisioningModel;
    private String machineType;
    private String region;
    private String localSSD;
    private String committedTerm;
    private String gpuModel;
    private String instancesNumber;
    private String gpuNumber;

    public ComputeEngine () {
        super();
        PRODUCT_NAME = "Compute Engine";
    }

    public ComputeEngine (String operationSystem, String provisioningModel, String machineType, String region, String localSSD, String committedTerm,
                   String gpuModel, String instancesNumber, String gpuNumber)
    {
        super();
        PRODUCT_NAME = "Compute Engine";
        this.operationSystem = operationSystem;
        this.provisioningModel = provisioningModel;
        this.machineType = machineType;
        this.region = region;
        this.localSSD = localSSD;
        this. committedTerm = committedTerm;
        this. gpuModel = gpuModel;
        this.instancesNumber = instancesNumber;
        this.gpuNumber = gpuNumber;
    }

    public String getProvisioningModel() {return provisioningModel;}

    public void setProvisioningModel(String provisioningModel) {this.provisioningModel = provisioningModel;}

    public String getGpuNumber() {
        return gpuNumber;
    }

    public void setGpuNumber(String gpuNumber) {
        this.gpuNumber = gpuNumber;
    }

    public String getInstancesNumber() {
        return instancesNumber;
    }

    public void setInstancesNumber(String instancesNumber) {
        this.instancesNumber = instancesNumber;
    }

    @Override
    public String getProductName() {
        return PRODUCT_NAME;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getCommittedTerm() {
        return committedTerm;
    }

    public void setCommittedTerm(String committedTerm) {
        this.committedTerm = committedTerm;
    }

    public String getGpuModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }
}
