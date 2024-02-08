package org.example.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.RoomDTO;
import org.example.exception.NegativeOrNullException;
import org.example.service.RoomService;
import repository.BookingHibernateRepository;
import repository.RoomHibernateRepository;
import repository.impl.RoomRepositoryImpl;

@Path("room")
public class RoomResource {
    private final RoomService roomService;

    public RoomResource(){
        roomService = new RoomService(new RoomRepositoryImpl(new RoomHibernateRepository(), new BookingHibernateRepository()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(RoomDTO roomDTO){
        try {
            roomService.createRoom(roomDTO.getCapacity());
            return Response.ok().build();
        } catch (NegativeOrNullException e){
            return Response.status(400,"La capacité d'une chambre doit être supérieure à 0").build();
        }
    }


}
