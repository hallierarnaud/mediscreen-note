package com.openclassrooms.mediscreennote;

import com.openclassrooms.mediscreennote.controller.DTO.NoteRequest;
import com.openclassrooms.mediscreennote.domain.object.Note;
import com.openclassrooms.mediscreennote.domain.service.NoteService;
import com.openclassrooms.mediscreennote.model.DAO.NoteDAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

  @Mock
  private NoteDAO noteDAO;

  @InjectMocks
  private NoteService noteService;

  @Test
  public void getNoteById_shouldReturnOk () {
    // GIVEN
    Note note = new Note();
    note.setId("1");
    note.setPatientNote("diabete");
    when(noteDAO.existById(anyString())).thenReturn(true);
    when(noteDAO.findById(anyString())).thenReturn(note);

    // WHEN
    Note actualNote = noteService.getNoteById(note.getId());

    // THEN
    assertEquals("diabete", actualNote.getPatientNote());
    verify(noteDAO, times(1)).existById(note.getId());
    verify(noteDAO, times(1)).findById(note.getId());
  }

  @Test
  public void getNoteById_shouldReturnNotFound () {
    // GIVEN
    Note note = new Note();
    note.setId("1");

    // WHEN

    when(noteDAO.existById(anyString())).thenReturn(false);

    // THEN
    assertThrows(NoSuchElementException.class, () -> noteService.getNoteById(note.getId()));
    verify(noteDAO).existById(anyString());
  }

  @Test
  public void getNotesByPatientId_shouldReturnOk () {
    // GIVEN
    List<Note> notes = new ArrayList<>();
    notes.add(new Note());
    when(noteDAO.findAllByPatientId(anyLong())).thenReturn(notes);

    // WHEN
    List<Note> actualNotes = noteService.getNotesByPatientId(anyLong());

    // THEN
    assertEquals(notes, actualNotes);
    verify(noteDAO, times(2)).findAllByPatientId(anyLong());
  }

  @Test
  public void getNotes_shouldReturnOk () {
    // GIVEN
    List<Note> notes = new ArrayList<>();
    notes.add(new Note());
    when(noteDAO.findAll()).thenReturn(notes);

    // WHEN
    List<Note> actualNotes = noteService.getNotes();

    // THEN
    assertEquals(notes, actualNotes);
    verify(noteDAO).findAll();
  }

  @Test
  public void updateNoteById_shouldReturnOk () {
    // GIVEN
    NoteRequest noteRequest = new NoteRequest();
    noteRequest.setPatientNote("diabete");
    Note note = new Note();
    note.setId("1");
    when(noteDAO.findById(anyString())).thenReturn(note);
    when(noteDAO.updateNoteById(anyString(), any(Note.class))).thenReturn(note);

    // WHEN
    Note updatedNote = noteService.updateNoteById(note.getId(), noteRequest);

    // THEN
    assertEquals(note.getPatientNote(), updatedNote.getPatientNote());
    verify(noteDAO, times(2)).findById(anyString());
    verify(noteDAO).updateNoteById(note.getId(), note);
  }

  @Test
  public void updateNoteById_shouldReturnNotFound () {
    // GIVEN
    Note note = new Note();
    note.setId("1");
    NoteRequest noteRequest = new NoteRequest();

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> noteService.updateNoteById(note.getId(), noteRequest));
    assertEquals("Note 1 doesn't exist", exception.getMessage());
  }

  @Test
  public void addNoteByPatientId_shouldReturnOk () {
    // GIVEN
    NoteRequest noteRequest = new NoteRequest();
    noteRequest.setPatientNote("diabete");
    Note note = new Note();
    when(noteDAO.addNoteByPatientId(any(Note.class))).thenReturn(note);

    // WHEN
    Note addedNote = noteService.addNoteByPatientId(noteRequest);

    // THEN
    assertEquals(note.getPatientNote(), addedNote.getPatientNote());
    verify(noteDAO).addNoteByPatientId(any(Note.class));
  }

  @Test
  public void deleteNoteById_shouldReturnOk () {
    // GIVEN
    Note note = new Note();
    note.setId("1");
    when(noteDAO.existById(anyString())).thenReturn(TRUE);

    // WHEN
    noteService.deleteNoteById(note.getId());

    // THEN
    verify(noteDAO).deleteNoteById(note.getId());
  }

  @Test
  public void deleteNoteById_shouldReturnNotFound () {
    // GIVEN
    Note note = new Note();
    note.setId("1");

    // WHEN
    when(noteDAO.existById(anyString())).thenReturn(FALSE);

    // THEN
    Throwable exception = assertThrows(NoSuchElementException.class, () -> noteService.deleteNoteById(note.getId()));
    assertEquals("Note 1 doesn't exist", exception.getMessage());
  }

}