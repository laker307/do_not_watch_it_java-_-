package com.example.one_more_language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.one_more_language.entity.Namespace;

public interface NamespaceRepository extends JpaRepository<Namespace, String> {
}
