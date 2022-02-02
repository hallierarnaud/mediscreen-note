package com.openclassrooms.mediscreennote.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class NoteEntity {

  @Id
  private ObjectId id;

  private long patientId;

  private String patientNote;

}
