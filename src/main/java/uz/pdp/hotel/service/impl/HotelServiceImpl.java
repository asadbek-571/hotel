package uz.pdp.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.model.HotelDto;
import uz.pdp.hotel.model.res.ApiResponse;
import uz.pdp.hotel.repository.HotelRepo;
import uz.pdp.hotel.service.HotelService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo repo;

    @Override
    public ResponseEntity<ApiResponse<Boolean>> delete(Long id) {
        Optional<Hotel> optionalHotel = repo.findById(id);
        if (!optionalHotel.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Hotel not found"), HttpStatus.NOT_FOUND);
        Hotel hotel = optionalHotel.get();
        repo.delete(hotel);
        return new ResponseEntity<>(new ApiResponse<>(true), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<HotelDto>> update(Long id, HotelDto dto) {
        Optional<Hotel> optionalHotel = repo.findById(id);
        if (!optionalHotel.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Hotel not found"), HttpStatus.NOT_FOUND);
        Hotel hotel = optionalHotel.get();
        hotel.setName(dto.getName());
        repo.save(hotel);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Hotel>> get(Long id) {
        Optional<Hotel> hotelOpt = repo.findById(id);
        return hotelOpt.map(hotel -> new ResponseEntity<>(new ApiResponse<>(hotel), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Hotel not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Hotel>>> getList() {
        return new ResponseEntity<>(new ApiResponse<>(repo.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<HotelDto>> save(HotelDto dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        repo.save(hotel);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.CREATED);

    }
}
