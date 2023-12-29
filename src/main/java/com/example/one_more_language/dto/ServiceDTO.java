package com.example.one_more_language.dto;
import com.example.one_more_language.entity.Service;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.example.one_more_language.entity.Namespace;
import org.springframework.format.annotation.DateTimeFormat;

import javax.naming.Name;

@Data
public class ServiceDTO {
    private Integer id;
    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private Integer port;

    @NotNull
    private String type;

    @NotNull
    private Namespace namespace;


    public Service toEntity() {
        Service service = new Service();
        service.setId(id);
        service.setName(name);
        service.setPort(port);
        service.setType(type);
        service.setNamespace(namespace);
        return service;
    }

}
