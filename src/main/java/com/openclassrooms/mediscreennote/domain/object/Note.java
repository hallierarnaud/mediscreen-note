package com.openclassrooms.mediscreennote.domain.object;

import lombok.Data;

@Data
public class Note {

  private String id;
  private long patientId;
  private String patientNote;

}
