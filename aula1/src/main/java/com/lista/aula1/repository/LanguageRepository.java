package com.lista.aula1.repository;

import com.lista.aula1.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLanguageIgnoreCase(String languageName);
}