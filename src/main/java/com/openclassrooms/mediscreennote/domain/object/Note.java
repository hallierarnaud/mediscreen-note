package com.openclassrooms.mediscreennote.domain.object;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Note {

  private ObjectId id;
  private long patientId;
  private String patientNote;

}
