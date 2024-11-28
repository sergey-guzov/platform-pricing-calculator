package org.github.guzov.product;

public enum Characteristics {
    OPERATION_SYSTEM ("Operating System / Software"),
    COMMITTED_TERM("Committed use discount options"),
    LOCAL_SSD("Local SSD"),
    GPU_MODEL("GPU Model"),
    REGION("Region"),
    MACHINE_TYPE("Machine type");
    private final String TITLE;

    Characteristics(String title)
    {
        this.TITLE = title;
    }

    public String getTitle()
    {
        return TITLE;
    }
}
