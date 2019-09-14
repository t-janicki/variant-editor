package com.variants.editor.web.request;

public class VariantRequest {
    private Long position;
    private String alteration;
    private String chromosome;
    private String description;

    public VariantRequest() {
    }

    public VariantRequest(Long position, String alteration,
                          String chromosome, String description) {
        this.position = position;
        this.alteration = alteration;
        this.chromosome = chromosome;
        this.description = description;
    }

    public Long getPosition() {
        return position;
    }

    public String getAlteration() {
        return alteration;
    }

    public String getChromosome() {
        return chromosome;
    }

    public String getDescription() {
        return description;
    }
}
