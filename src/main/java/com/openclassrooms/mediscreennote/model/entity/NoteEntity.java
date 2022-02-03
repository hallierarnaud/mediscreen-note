package com.openclassrooms.mediscreennote.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class NoteEntity {

  @Id
  private String id;

  private long patientId;

  private String patientNote;

}
