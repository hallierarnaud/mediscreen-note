package com.openclassrooms.mediscreennote.domain.service;

import com.openclassrooms.mediscreennote.model.DAO.NoteDAO;
import com.openclassrooms.mediscreennote.model.entity.NoteEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

  @Autowired
  private NoteDAO noteDAO;

  public List<NoteEntity> getAllPatientNotes() {
    return noteDAO.getAllPatientNotes();
  }

}
