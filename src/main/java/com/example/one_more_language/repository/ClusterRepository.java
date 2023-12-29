package com.example.one_more_language.repository;
import com.example.one_more_language.entity.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClusterRepository
    extends JpaRepository<Cluster, String> {
}

