package com.openclassrooms.mediscreennote.controller.DTO;

import lombok.Data;

@Data
public class NoteRequest {

  private long patientId;
  private String patientNote;

}
