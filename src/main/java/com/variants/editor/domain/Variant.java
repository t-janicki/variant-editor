package com.variants.editor.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VARIANTS")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "POSITION")
    private Long position;

    @Column(name = "ALTERATION")
    private String alteration;

    @Column(name = "CHROMOSOME")
    private String chromosome;

    @Column(name = "DESCRIPTION")
    @Size(max = 5000)
    private String description;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="VARIANTS_USERS",
            uniqueConstraints={
                    @UniqueConstraint(columnNames={"VARIANT_ID","USERS_ID"}),
            }
    )
    private List<User> users = new ArrayList<>();

    public Variant() {
    }

    public Variant(Long id, Long position, String alteration,
                   String chromosome, String description) {
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

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getAlteration() {
        return alteration;
    }

    public void setAlteration(String alteration) {
        this.alteration = alteration;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
