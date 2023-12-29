package com.example.one_more_language.controller;

import com.example.one_more_language.entity.Cluster;
import com.example.one_more_language.dto.ClusterDTO;
import com.example.one_more_language.repository.ClusterRepository;
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
@RequestMapping("/cluster")
@RequiredArgsConstructor
public class ClusterController {
    private final ClusterRepository clusterRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute( "list", clusterRepository.findAll());
        return "cluster";
    }

    @GetMapping("/init")
    public String init() {
        Cluster cluster = new Cluster();
        cluster.setName("Cluster-1");
        cluster.setCloud("Yandex");
        cluster.setSize(1);
        cluster.setId(0);

        clusterRepository.save(cluster);
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
