package com.example.one_more_language.dto;
import com.example.one_more_language.entity.Pod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.example.one_more_language.entity.Namespace;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PodDTO {
    private Integer id;
    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String labels;

    @NotNull
    private String containers;

    @NotNull
    private Namespace namespace;


    public Pod toEntity() {
        Pod pod = new Pod();
        pod.setId(id);
        pod.setName(name);
        pod.setLabels(labels);
        pod.setContainers(containers);
        pod.setNamespace(namespace);
        return pod;
    }

}
