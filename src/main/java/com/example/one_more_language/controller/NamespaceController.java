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
public class ClusterController {
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
        return "redirect:/cluster/";
    }

    @PostMapping("/add")
    public String add(@Valid ClusterDTO cluster, BindingResult result, Model model) {
        log.info("add cluster {}", cluster);
        if (!result.hasErrors()) {
            clusterRepository.save(cluster.toEntity());
        }
        return index(model);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Optional<Cluster> cluster = clusterRepository.findById(id);
        cluster.ifPresent(value -> {
            model.addAttribute("clusterDTO", value);
        });

        return "edit-cluster";
    }

    @PostMapping("edit")
    public String edit(@Valid ClusterDTO request, HttpServletRequest servletRequest, BindingResult result) {
        if (!result.hasErrors()) {
            Cluster cluster = request.toEntity();
            clusterRepository.save(cluster);
        }
        return "redirect:/cluster/";
    }

    /*@GetMapping("{clusterId}")
    public String artist(@PathVariable Strng clusterId, Model model) {
        model.addAttribute("artist", clusterRepository.findById(clusterId).get());
        return "cluster-view";
    }*/

    @PostMapping("/remove")
    public String remove(String id, Model model) {
        log.info("remove cluster with id {}", id);
        clusterRepository.deleteById(id);
        return index(model);


    }
}
