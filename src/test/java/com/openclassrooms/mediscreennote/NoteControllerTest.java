package com.openclassrooms.mediscreennote;

import com.openclassrooms.mediscreennote.controller.endpoint.NoteController;
import com.openclassrooms.mediscreennote.domain.object.Note;
import com.openclassrooms.mediscreennote.domain.service.NoteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NoteService noteService;

  @Test
  public void getNoteById_shouldReturnOk() throws Exception {
    when(noteService.getNoteById(any())).thenReturn(new Note());
    mockMvc.perform(get("/note/1")).andExpect(status().isOk());
  }

  @Test
  public void getNoteById_shouldReturnNotFound() throws Exception {
    when(noteService.getNoteById(any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(get("/note/1")).andExpect(status().isNotFound());
  }

  @Test
  public void getNotesByPatientId_shouldReturnOk() throws Exception {
    when(noteService.getNotesByPatientId(any())).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/notes/1")).andExpect(status().isOk());
  }

  @Test
  public void getNotes_shouldReturnOk() throws Exception {
    when(noteService.getNotes()).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/notes")).andExpect(status().isOk());
  }

  @Test
  public void updateNoteById_shouldReturnOk() throws Exception {
    when(noteService.updateNoteById(any(), any())).thenReturn(new Note());
    mockMvc.perform(put("/notes/1")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void updateNoteById_shouldReturnUnprocessableEntity() throws Exception {
    when(noteService.updateNoteById(any(), any())).thenThrow(NoSuchElementException.class);
    mockMvc.perform(put("/notes/1")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void addNoteByPatientId_shouldReturnOk() throws Exception {
    when(noteService.addNoteByPatientId(anyLong(), any())).thenReturn(new Note());
    mockMvc.perform(post("/notes/1")
                    .contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isOk());
  }

  @Test
  public void deleteNoteById_shouldReturnOk() throws Exception {
    doNothing().when(noteService).deleteNoteById(any());
    mockMvc.perform(delete("/notes/1")).andExpect(status().isOk());
  }

  @Test
  public void deleteNoteById_shouldReturnNotFound() throws Exception {
    doThrow(NoSuchElementException.class).when(noteService).deleteNoteById(any());
    mockMvc.perform(delete("/notes/1")).andExpect(status().isNotFound());
  }

}