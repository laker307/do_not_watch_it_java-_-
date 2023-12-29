package com.example.one_more_language.repository;
import com.example.one_more_language.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository <Service, String> {
}
