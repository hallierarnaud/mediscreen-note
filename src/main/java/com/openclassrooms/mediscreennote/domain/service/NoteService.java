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

  public Note getNoteById(final ObjectId id) {
    if (!noteDAO.existById(id)) {
      throw new NoSuchElementException("Note " + id + " doesn't exist");
    }
    return noteDAO.findById(id);
  }

  public List<Note> getNotesByPatientId(final Long patientId) {
    if (noteDAO.findAllByPatientId(patientId) == null) {
      throw new NoSuchElementException("Patient " + patientId + " has no note");
    }
    return noteDAO.findAllByPatientId(patientId);
  }

  public List<Note> getNotes() {
    return StreamSupport.stream(noteDAO.findAll().spliterator(),false)
            .collect(Collectors.toList());
  }

  public Note updateNoteById(final ObjectId id, NoteRequest noteRequest) {
    if (noteDAO.findById(id) == null) {
      throw new NoSuchElementException("Note " + id + " doesn't exist");
    }
    Note note = noteDAO.findById(id);
    note.setId(id);
    note.setPatientId(noteRequest.getPatientId());
    note.setPatientNote(noteRequest.getPatientNote());
    return noteDAO.updateNoteById(id, note);
  }

  public Note addNoteByPatientId(NoteRequest noteRequest) {
    Note note = new Note();
    note.setPatientId(noteRequest.getPatientId());
    note.setPatientNote(noteRequest.getPatientNote());
    return noteDAO.addNoteByPatientId(note);
  }

  public void deleteNoteById(final ObjectId id) {
    if (!noteDAO.existById(id)) {
      throw new NoSuchElementException("Note " + id + " doesn't exist");
    }
    noteDAO.deleteNoteById(id);
  }

}
