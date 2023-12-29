package com.example.one_more_language.controller;

import com.example.one_more_language.entity.Namespace;
import com.example.one_more_language.dto.NamespaceDTO;
import com.example.one_more_language.repository.NamespaceRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/namespace")
@RequiredArgsConstructor
public class NamespaceController {
    private final NamespaceRepository namespaceRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute( "list", namespaceRepository.findAll());
        return "namespace";
    }

    @GetMapping("/init")
    public String init() {
        Namespace namespace = new Namespace();
        namespace.setName("some-namespace");
        namespace.setResources(0);
        namespace.setPolicy("Base-policy");
        namespace.setId(0);

        namespaceRepository.save(namespace);
        return "redirect:/namespace/";
    }

    @PostMapping("/add")
    public String add(@Valid NamespaceDTO namespace, BindingResult result, Model model) {
        log.info("add namespace {}", namespace);
        if (!result.hasErrors()) {
            namespaceRepository.save(namespace.toEntity());
        }
        return index(model);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Optional<Namespace> namespace = namespaceRepository.findById(id);
        namespace.ifPresent(value -> {
            model.addAttribute("namespaceDTO", value);
        });

        return "edit-namespace";
    }

    @PostMapping("edit")
    public String edit(@Valid NamespaceDTO request, HttpServletRequest servletRequest, BindingResult result) {
        if (!result.hasErrors()) {
            Namespace namespace = request.toEntity();
            namespaceRepository.save(namespace);
        }
        return "redirect:/namespace/";
    }

    /*@GetMapping("{namespaceId}")
    public String artist(@PathVariable Strng namespaceId, Model model) {
        model.addAttribute("artist", namespaceRepository.findById(namespaceId).get());
        return "namespace-view";
    }*/

    @PostMapping("/remove")
    public String remove(String id, Model model) {
        log.info("remove namespace with id {}", id);
        namespaceRepository.deleteById(id);
        return index(model);


    }
}
