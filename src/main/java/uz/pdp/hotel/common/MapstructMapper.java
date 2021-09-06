package uz.pdp.hotel.common;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.model.RoomDto;

@Component
@Mapper(componentModel = "string",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    @Mapping(target = "hotelId",source = "hotel.id")
    RoomDto toRoomDto(Room room);

    @Mapping(target = "hotel.id",source = "hotelId")
    Room toRoom(RoomDto dto);
}
