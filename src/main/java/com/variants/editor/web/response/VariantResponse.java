package com.variants.editor.web.response;

public class VariantResponse {
    private Long id;
    private Long position;
    private String alteration;
    private String chromosome;
    private String description;

    public VariantResponse() {
    }

    public VariantResponse(Long id, Long position,
                           String alteration, String chromosome, String description) {
        this.id = id;
        this.position = position;
        this.alteration = alteration;
        this.chromosome = chromosome;
        this.description = description;
    }

    public Long getId() {
        return id;
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
