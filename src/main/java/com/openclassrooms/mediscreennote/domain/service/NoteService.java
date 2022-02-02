package com.openclassrooms.mediscreennote.domain.service;

import com.openclassrooms.mediscreennote.controller.DTO.NoteRequest;
import com.openclassrooms.mediscreennote.domain.object.Note;
import com.openclassrooms.mediscreennote.model.DAO.NoteDAO;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoteService {

  @Autowired
  private NoteDAO noteDAO;

  public List<Note> getNotesByPatientId(final Long patientId) {
    if (noteDAO.findAllByPatientId(patientId) == null) {
      throw new NoSuchElementException("Patient " + patientId + " have no note");
    }
    return noteDAO.findAllByPatientId(patientId);
  }

  public List<Note> getNotes() {
    return StreamSupport.stream(noteDAO.findAll().spliterator(),false)
            .collect(Collectors.toList());
  }

  public Note updateNote(final ObjectId id, NoteRequest noteRequest) {
    if (noteDAO.findById(id) == null) {
      throw new NoSuchElementException("Note " + id + " doesn't exist");
    }
    Note note = noteDAO.findById(id);
    note.setId(id);
    note.setPatientNote(noteRequest.getPatientNote());
    return noteDAO.updateNote(id, note);
  }

  public Note addNote(NoteRequest noteRequest) {
    /*if (noteDAO.existById(noteRequest.getId()) {
      throw new EntityExistsException("Note " + noteRequest.getId() + " already exists");
    }*/
    Note note = new Note();
    note.setPatientNote(noteRequest.getPatientNote());
    return noteDAO.addNote(note);
  }

  public void deleteNote(final ObjectId id) {
    if (!noteDAO.existById(id)) {
      throw new NoSuchElementException("Note " + id + " doesn't exist");
    }
    noteDAO.deleteNoteById(id);
  }

}
