package uz.pdp.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.model.HotelDto;
import uz.pdp.hotel.model.RoomDto;
import uz.pdp.hotel.model.res.ApiResponse;
import uz.pdp.hotel.service.HotelService;
import uz.pdp.hotel.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;



    @PostMapping("/save")
    public ResponseEntity<ApiResponse<HotelDto>> save(@RequestBody HotelDto dto ){
        return service.save(dto);
    }


    @GetMapping("/getList/")
    public ResponseEntity<ApiResponse<List<Hotel>>> getList(){
        return service.getList();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Hotel>> get(@PathVariable(value = "id")Long id){
        return service.get(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<HotelDto>> update(@RequestBody HotelDto dto,@PathVariable(value = "id")Long id){
        return service.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }


}
