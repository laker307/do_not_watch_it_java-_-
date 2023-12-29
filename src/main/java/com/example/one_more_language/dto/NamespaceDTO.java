package com.example.one_more_language.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.example.one_more_language.entity.Cluster;
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
    private Integer size;


    public Cluster toEntity() {
        Cluster cluster = new Cluster();
        cluster.setId(id);
        cluster.setName(name);
        cluster.setCloud(cloud);
        cluster.setSize(size);
        return cluster;
    }

}
