package com.openclassrooms.mediscreennote;

import com.openclassrooms.mediscreennote.domain.object.Note;
import com.openclassrooms.mediscreennote.model.DAO.NoteDAO;
import com.openclassrooms.mediscreennote.model.entity.NoteEntity;
import com.openclassrooms.mediscreennote.model.repository.NoteRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteDAOTest {

  @Mock
  private NoteRepository noteRepository;

  @InjectMocks
  private NoteDAO noteDAO;

  @Test
  public void findById_shouldReturnOk() {
    // GIVEN
    NoteEntity noteEntity = new NoteEntity();
    Note note = new Note();
    when(noteRepository.findById(anyString())).thenReturn(java.util.Optional.of(noteEntity));

    // WHEN
    Note actualNote = noteDAO.findById(anyString());

    // THEN
    assertEquals(note, actualNote);
    verify(noteRepository).findById(anyString());
  }

  @Test
  public void findById_shouldReturnNotFound() {
    // GIVEN
    Note note = new Note();
    note.setId("1");

    // THEN
    assertThrows(NoSuchElementException.class, () -> noteDAO.findById(note.getId()));
  }

  @Test
  public void findAllByPatientId_shouldReturnOk() {
    // GIVEN
    List<NoteEntity> noteEntities = new ArrayList<>();
    noteEntities.add(new NoteEntity());
    List<Note> notes = new ArrayList<>();
    notes.add(new Note());
    when(noteRepository.findAllByPatientId(anyLong())).thenReturn(noteEntities);

    // WHEN
    List<Note> actualNotes = noteDAO.findAllByPatientId(anyLong());

    // THEN
    assertEquals(notes, actualNotes);
    verify(noteRepository).findAllByPatientId(anyLong());
  }

  @Test
  public void findAll_shouldReturnOk() {
    // GIVEN
    List<NoteEntity> noteEntities = new ArrayList<>();
    noteEntities.add(new NoteEntity());
    List<Note> notes = new ArrayList<>();
    notes.add(new Note());
    when(noteRepository.findAll()).thenReturn(noteEntities);

    // WHEN
    List<Note> actualNotes = noteDAO.findAll();

    // THEN
    assertEquals(notes, actualNotes);
    verify(noteRepository).findAll();
  }

  @Test
  public void updateNoteById_shouldReturnOK() {
    // GIVEN
    NoteEntity noteEntity = new NoteEntity();
    Note note = new Note();
    note.setId("1");
    note.setPatientNote("diabete");
    when(noteRepository.findById(anyString())).thenReturn(java.util.Optional.of(noteEntity));
    when(noteRepository.save(any(NoteEntity.class))).thenReturn(noteEntity);

    // WHEN
    Note updatedNote = noteDAO.updateNoteById("1", note);

    // THEN
    assertEquals("diabete", updatedNote.getPatientNote());
    verify(noteRepository).findById(anyString());
    verify(noteRepository).save(any(NoteEntity.class));
  }

  @Test
  public void updateNoteById_shouldReturnNotFound() {
    // GIVEN
    Note note = new Note();
    note.setId("1");

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> noteDAO.updateNoteById(note.getId(), note));
    assertEquals("note 1 doesn't exist", exception.getMessage());
  }

  @Test
  public void addNoteByPatientId_shouldReturnOK() {
    // GIVEN
    NoteEntity noteEntity = new NoteEntity();
    Note note = new Note();
    note.setId("1");
    note.setPatientNote("diabete");
    when(noteRepository.save(any(NoteEntity.class))).thenReturn(noteEntity);

    // WHEN
    Note addedNote = noteDAO.addNoteByPatientId(note);

    // THEN
    assertEquals("diabete", addedNote.getPatientNote());
    verify(noteRepository).save(any(NoteEntity.class));
  }

  @Test
  public void deleteNoteById_shouldReturnOk() {
    // GIVEN
    NoteEntity noteEntity = new NoteEntity();
    noteEntity.setId("1");

    // WHEN
    noteDAO.deleteNoteById(noteEntity.getId());

    // THEN
    verify(noteRepository).deleteById(anyString());
  }

  @Test
  public void existById_shouldReturnOk() {
    // GIVEN
    when(noteRepository.existsById(anyString())).thenReturn(true);

    // WHEN
    Boolean actual = noteDAO.existById(anyString());

    // THEN
    assertEquals(true, actual);
    verify(noteRepository).existsById(anyString());
  }

}