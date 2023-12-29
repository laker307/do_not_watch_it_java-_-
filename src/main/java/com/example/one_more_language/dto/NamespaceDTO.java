package com.example.one_more_language.dto;
import com.example.one_more_language.entity.Cluster;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.example.one_more_language.entity.Namespace;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class NamespaceDTO {
    private Integer id;
    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String policy;

    @NotNull
    private Integer resources;

    @NotNull
    private Cluster cluster;


    public Namespace toEntity() {
        Namespace namespace = new Namespace();
        namespace.setId(id);
        namespace.setName(name);
        namespace.setPolicy(policy);
        namespace.setResources(resources);
        namespace.setCluster(cluster);
        return namespace;
    }

}
