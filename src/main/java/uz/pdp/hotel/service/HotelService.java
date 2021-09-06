package uz.pdp.hotel.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.model.HotelDto;
import uz.pdp.hotel.model.res.ApiResponse;

import java.util.List;

public interface HotelService {
    ResponseEntity<ApiResponse<Boolean>> delete(Long id);

    ResponseEntity<ApiResponse<HotelDto>> update(Long id, HotelDto dto);

    ResponseEntity<ApiResponse<Hotel>> get(Long id);

    ResponseEntity<ApiResponse<List<Hotel>>> getList();

    ResponseEntity<ApiResponse<HotelDto>> save(HotelDto dto);
}
