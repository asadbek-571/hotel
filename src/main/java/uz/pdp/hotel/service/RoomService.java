package uz.pdp.hotel.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.model.RoomDto;
import uz.pdp.hotel.model.res.ApiResponse;

import java.util.List;

public interface RoomService {
    ResponseEntity<ApiResponse<List<Room>>> getPage(Integer page, Integer size, Long id);

    ResponseEntity<ApiResponse<RoomDto>> update(Long id, RoomDto dto);

    ResponseEntity<ApiResponse<RoomDto>> save(RoomDto dto);

    ResponseEntity<ApiResponse<Boolean>> delete(Long id);


    ResponseEntity<ApiResponse<Room>> get(Long id);
}
