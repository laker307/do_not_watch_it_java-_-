package com.example.one_more_language.controller;

import com.example.one_more_language.entity.Service;
import com.example.one_more_language.dto.ServiceDTO;
import com.example.one_more_language.repository.ServiceRepository;
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
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceRepository serviceRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute( "list", serviceRepository.findAll());
        return "service";
    }

    @GetMapping("/init")
    public String init() {
        Service service = new Service();
        service.setName("some-service");
        service.setPort(8080);
        service.setType("LoadBalancer");
        service.setId(0);

        serviceRepository.save(service);
        return "redirect:/service/";
    }

    @PostMapping("/add")
    public String add(@Valid ServiceDTO service, BindingResult result, Model model) {
        log.info("add service {}", service);
        if (!result.hasErrors()) {
            serviceRepository.save(service.toEntity());
        }
        return index(model);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Optional<Service> service = serviceRepository.findById(id);
        service.ifPresent(value -> {
            model.addAttribute("serviceDTO", value);
        });

        return "edit-service";
    }

    @PostMapping("edit")
    public String edit(@Valid ServiceDTO request, HttpServletRequest servletRequest, BindingResult result) {
        if (!result.hasErrors()) {
            Service service = request.toEntity();
            serviceRepository.save(service);
        }
        return "redirect:/service/";
    }

    /*@GetMapping("{serviceId}")
    public String artist(@PathVariable Strng serviceId, Model model) {
        model.addAttribute("artist", serviceRepository.findById(serviceId).get());
        return "service-view";
    }*/

    @PostMapping("/remove")
    public String remove(String id, Model model) {
        log.info("remove service with id {}", id);
        serviceRepository.deleteById(id);
        return index(model);


    }
}
