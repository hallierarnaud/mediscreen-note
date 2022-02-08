package com.openclassrooms.mediscreennote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteIt {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetNoteById_shouldReturnOk() throws Exception {
      mockMvc.perform(get("/note/61fce15a2f0bd332a924deed"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.patientNote", is("diabete type 2")));
    }

    @Test
    public void testGetNoteById_shouldReturnNotFound() throws Exception {
      mockMvc.perform(get("/note/61fce15a2f0bd332a924deef"))
              .andExpect(status().isNotFound());
    }

    @Test
    public void testGetNotesByPatientId_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/notes/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientNote", is("diabete type 2")));
    }

    @Test
    public void testGetNotes_shouldReturnOk() throws Exception {
      mockMvc.perform(get("/notes"))
              .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNoteById_shouldReturnOk() throws Exception {
      mockMvc.perform(put("/notes/61fce15a2f0bd332a924deed")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"patientId\":\"7\",\"patientNote\":\"diabete type 2\"}"))
              .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNoteById_shouldReturnUnprocessableEntity() throws Exception {
      mockMvc.perform(put("/notes/61fce15a2f0bd332a924deef")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"patientId\":\"7\"}"))
              .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testPostNote_shouldReturnOk() throws Exception {
      mockMvc.perform(post("/notes")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"patientId\":\"1\",\"patientNote\":\"test\"}"))
              .andExpect(status().isOk());
    }

    //TODO: delete note by id after getting it
    /*@Test
    public void testDeleteNote_shouldReturnOk() throws Exception {
      mockMvc.perform(delete("/notes/1"))
              .andExpect(status().isOk());
    }*/

    @Test
    public void testDeleteNote_shouldReturnNotFound() throws Exception {
      mockMvc.perform(delete("/notes/61fce15a2f0bd332a924deef"))
              .andExpect(status().isNotFound());
    }

}
