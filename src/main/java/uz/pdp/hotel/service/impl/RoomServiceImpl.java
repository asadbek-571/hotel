package uz.pdp.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.hotel.common.MapstructMapper;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.model.RoomDto;
import uz.pdp.hotel.model.res.ApiResponse;
import uz.pdp.hotel.repository.HotelRepo;
import uz.pdp.hotel.repository.RoomRepo;
import uz.pdp.hotel.service.HotelService;
import uz.pdp.hotel.service.RoomService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final   RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
//    private final MapstructMapper mapstructMapper;


    @Override
    public ResponseEntity<ApiResponse<List<Room>>> getPage(Integer page, Integer size, Long id) {
        Optional<Hotel> hotel = hotelRepo.findById(id);
        if (!hotel.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Hotel not found"), HttpStatus.NOT_FOUND);

        List<Room> roomList = roomRepo.findAllByHotelId(size, (size * page), id);

        return new ResponseEntity<>(new ApiResponse<>(roomList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<RoomDto>> update(Long id, RoomDto dto) {

        Optional<Hotel> hotel = hotelRepo.findById(dto.getHotelId());
        if (!hotel.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Hotel not found"), HttpStatus.NOT_FOUND);

        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Room not found"), HttpStatus.NOT_FOUND);

        Room room = optionalRoom.get();
        room.setHotel(hotel.get());
        room.setFloor(dto.getFloor());
        room.setNumber(dto.getNumber());
        room.setSize(dto.getSize());
        roomRepo.save(room);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<RoomDto>> save(RoomDto dto) {
        Optional<Hotel> hotel = hotelRepo.findById(dto.getHotelId());
        if (!hotel.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Hotel not found"), HttpStatus.NOT_FOUND);

        Room room = new Room();
        room.setFloor(dto.getFloor());
        room.setHotel(hotel.get());
        room.setNumber(dto.getNumber());
        room.setSize(dto.getSize());
        roomRepo.save(room);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Boolean>> delete(Long id) {

        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Room not found"), HttpStatus.NOT_FOUND);
        Room room = optionalRoom.get();
        roomRepo.delete(room);
        return new ResponseEntity<>(new ApiResponse<>(true), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<Room>> get(Long id) {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        return optionalRoom.map(room -> new ResponseEntity<>(new ApiResponse<>(room), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Room not found"), HttpStatus.NOT_FOUND));
    }
}
