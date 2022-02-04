package com.openclassrooms.mediscreennote.model.DAO;

import com.openclassrooms.mediscreennote.domain.object.Note;
import com.openclassrooms.mediscreennote.model.entity.NoteEntity;
import com.openclassrooms.mediscreennote.model.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class NoteDAO {

  @Autowired
  private NoteRepository noteRepository;

  public Note findById(String id) {
    NoteEntity noteEntity = noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("note " + id + "doesn't exist"));
    Note note = new Note();
    updateNoteWithNoteEntity(note, noteEntity);
    return note;
  }

  public List<Note> findAllByPatientId(Long patientId) {
    List<NoteEntity> noteEntities = StreamSupport.stream(noteRepository.findAllByPatientId(patientId).spliterator(), false)
            .collect(Collectors.toList());
    return noteEntities.stream().map((noteEntity) -> {
      Note note = new Note();
      updateNoteWithNoteEntity(note, noteEntity);
      return note;
    }).collect(Collectors.toList());
  }

  public List<Note> findAll() {
    List<NoteEntity> noteEntities = StreamSupport.stream(noteRepository.findAll().spliterator(),false)
            .collect(Collectors.toList());
    return noteEntities.stream().map((noteEntity) -> {
      Note note = new Note();
      updateNoteWithNoteEntity(note, noteEntity);
      return note;
    }).collect(Collectors.toList());
  }

  public Note updateNoteById(String id, Note note) {
    NoteEntity noteEntity = noteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("note " + id + " doesn't exist"));
    noteEntity.setId(note.getId());
    noteEntity.setPatientId(note.getPatientId());
    noteEntity.setPatientNote(note.getPatientNote());
    noteRepository.save(noteEntity);
    return note;
  }

  public Note addNoteByPatientId(Note note) {
    NoteEntity noteEntity = new NoteEntity();
    updateNoteEntityWithNote(noteEntity, note);
    noteRepository.save(noteEntity);
    return note;
  }

  public void deleteNoteById(String id) {
    noteRepository.deleteById(id);
  }

  public boolean existById(String id) {
    return noteRepository.existsById(id);
  }

  public Note updateNoteWithNoteEntity (Note note, NoteEntity noteEntity) {
    note.setId(noteEntity.getId());
    note.setPatientId(noteEntity.getPatientId());
    note.setPatientNote(noteEntity.getPatientNote());
    return note;
  }

  public NoteEntity updateNoteEntityWithNote (NoteEntity noteEntity, Note note) {
    noteEntity.setId(note.getId());
    noteEntity.setPatientId(note.getPatientId());
    noteEntity.setPatientNote(note.getPatientNote());
    return noteEntity;
  }

}
