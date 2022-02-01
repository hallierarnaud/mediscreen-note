package com.openclassrooms.mediscreennote;

import com.openclassrooms.mediscreennote.model.entity.NoteEntity;
import com.openclassrooms.mediscreennote.model.repository.NoteRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class MediscreenNoteApplication {

  public static void main(String[] args) {
    SpringApplication.run(MediscreenNoteApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(NoteRepository noteRepository) {
    return args -> {
      NoteEntity noteEntity = new NoteEntity(7, List.of("diabete type 2", "pancreatite"));
      noteRepository.insert(noteEntity);
    };
  }

}
