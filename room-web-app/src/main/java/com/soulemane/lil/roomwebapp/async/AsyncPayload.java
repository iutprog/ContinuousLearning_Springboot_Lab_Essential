package com.soulemane.lil.roomwebapp.async;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/** Represents the model type and ID in an asynchronous message. */
@Data 
public class AsyncPayload {
    /** Unique ID of the entity referenced by the message. */
@JsonProperty("id")
private UUID Id;
    /** Entity type referenced by the message, such as "ROOM". */
private String model;


}
