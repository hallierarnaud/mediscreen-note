package com.openclassrooms.mediscreennote.controller.endpoint;

import com.openclassrooms.mediscreennote.domain.service.NoteService;
import com.openclassrooms.mediscreennote.model.entity.NoteEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

  @Autowired
  private NoteService noteService;

  @GetMapping
  public List<NoteEntity> fetchAllPatientNotes() {
    return noteService.getAllPatientNotes();
  }

}
