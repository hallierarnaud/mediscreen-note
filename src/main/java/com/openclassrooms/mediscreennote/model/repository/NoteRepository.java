package com.openclassrooms.mediscreennote.model.repository;

import com.openclassrooms.mediscreennote.model.entity.NoteEntity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {

  Iterable<NoteEntity> findAllByPatientId (Long patientId);

}
