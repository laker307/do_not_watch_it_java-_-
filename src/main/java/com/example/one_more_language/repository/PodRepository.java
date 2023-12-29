package com.example.one_more_language.repository;

import com.example.one_more_language.entity.Pod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodRepository extends JpaRepository <Pod, String> {
}
