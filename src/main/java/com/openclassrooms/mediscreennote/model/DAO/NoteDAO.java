package com.openclassrooms.mediscreennote.model.DAO;

import com.openclassrooms.mediscreennote.model.entity.NoteEntity;
import com.openclassrooms.mediscreennote.model.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDAO {

  @Autowired
  private NoteRepository noteRepository;

  public List<NoteEntity> getAllPatientNotes() {
    return noteRepository.findAll();
  }

}
