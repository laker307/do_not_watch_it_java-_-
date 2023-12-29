package com.example.one_more_language.controller;

import com.example.one_more_language.entity.Pod;
import com.example.one_more_language.dto.PodDTO;
import com.example.one_more_language.repository.PodRepository;
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
@RequestMapping("/pod")
@RequiredArgsConstructor
public class PodController {
    private final PodRepository podRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute( "list", podRepository.findAll());
        return "pod";
    }

    @GetMapping("/init")
    public String init() {
        Pod pod = new Pod();
        pod.setName("some-pod");
        pod.setLabels("app:bla-bla,base-app:bla");
        pod.setContainers("gitlab,postgres");
        pod.setId(0);

        podRepository.save(pod);
        return "redirect:/pod/";
    }

    @PostMapping("/add")
    public String add(@Valid PodDTO pod, BindingResult result, Model model) {
        log.info("add pod {}", pod);
        if (!result.hasErrors()) {
            podRepository.save(pod.toEntity());
        }
        return index(model);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Optional<Pod> pod = podRepository.findById(id);
        pod.ifPresent(value -> {
            model.addAttribute("podDTO", value);
        });

        return "edit-pod";
    }

    @PostMapping("edit")
    public String edit(@Valid PodDTO request, HttpServletRequest servletRequest, BindingResult result) {
        if (!result.hasErrors()) {
            Pod pod = request.toEntity();
            podRepository.save(pod);
        }
        return "redirect:/pod/";
    }

    /*@GetMapping("{podId}")
    public String artist(@PathVariable Strng podId, Model model) {
        model.addAttribute("artist", podRepository.findById(podId).get());
        return "pod-view";
    }*/

    @PostMapping("/remove")
    public String remove(String id, Model model) {
        log.info("remove pod with id {}", id);
        podRepository.deleteById(id);
        return index(model);


    }
}
