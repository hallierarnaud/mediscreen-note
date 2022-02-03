package com.openclassrooms.mediscreennote.controller.endpoint;

import com.openclassrooms.mediscreennote.controller.DTO.NoteRequest;
import com.openclassrooms.mediscreennote.domain.object.Note;
import com.openclassrooms.mediscreennote.domain.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class NoteController {

  @Autowired
  private NoteService noteService;

  /**
   * @param id a note's id
   * @return a note corresponding to the id
   */
  @GetMapping("/note/{id}")
  public Note getNoteById(@PathVariable("id") String id) {
    try {
      return noteService.getNoteById(id);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "note " + id + " doesn't exist");
    }
  }

  /**
   * @param patientId a patient's id
   * @return a list of all notes corresponding to the patientId
   */
  @GetMapping("/notes/{patientId}")
  public List<Note> getNotesByPatientId(@PathVariable("patientId") long patientId) {
    try {
      return noteService.getNotesByPatientId(patientId);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "patient " + patientId + " has no note");
    }
  }

  /**
   * @return a list of all notes in the database
   */
  @GetMapping("/notes")
  public List<Note> getNotes() {
    return noteService.getNotes();
  }

  /**
   * @param id a note's id
   * @param noteRequest a note defined by his attributes
   * @return update the note in the database
   */
  @PutMapping("/notes/{id}")
  public Note updateNoteById(@PathVariable("id") String id, @RequestBody NoteRequest noteRequest) {
    try {
      return noteService.updateNoteById(id, noteRequest);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "note " + id + " doesn't exist");
    }
  }

  /**
   * @param noteRequest a note defined by his attributes
   * @return add the note to the database
   */
  @PostMapping("/notes")
  public Note addNoteByPatientId(@RequestBody NoteRequest noteRequest) {
    return noteService.addNoteByPatientId(noteRequest);
  }

  /**
   * @param id a note's id
   * delete the note in the database
   */
  @DeleteMapping("/notes/{id}")
  public void deleteNoteById(@PathVariable("id") String id) {
    try {
      noteService.deleteNoteById(id);
      throw new ResponseStatusException(HttpStatus.OK, "note " + id + " was deleted");
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "note " + id + " doesn't exist");
    }
  }

}
