package com.example.codepresent.repository;

import com.example.codepresent.domain.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlideRepository extends JpaRepository<Slide, UUID> {
}
