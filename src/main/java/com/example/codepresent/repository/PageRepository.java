package com.example.codepresent.repository;

import com.example.codepresent.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PageRepository extends JpaRepository<Page, UUID> {
}
