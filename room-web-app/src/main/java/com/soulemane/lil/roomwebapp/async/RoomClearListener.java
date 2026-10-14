package com.soulemane.lil.roomwebapp.async;

import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import com.soulemane.lil.roomwebapp.service.RoomService;
import com.soulemane.lil.roomwebapp.web.model.Room;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component 
public class RoomClearListener {
    private static final Logger LOG = LoggerFactory.getLogger(RoomClearListener.class);

    private final RoomService roomService;
    private final ObjectMapper mapper;

    /** Creates a listener with the room service and JSON mapper it needs. */
    public RoomClearListener(RoomService roomService, ObjectMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    /** Parses a room-cleaning message and logs the matching room details. */
    public void receiveMessage(String message){
    try{
      AsyncPayload payload = mapper.readValue(message, AsyncPayload.class);
      if("ROOM".equals(payload.getModel())){
        Room room = roomService.getRoomById(payload.getId());
        LOG.info("ROOM {}:{} needs to be cleaned", room.getNumber(), room.getName());
      }else{
        LOG.warn("Unknown model type");
      }
    }catch (JacksonException e){
      LOG.error("Failed to parse room-clear message", e);
    }
  }
}
