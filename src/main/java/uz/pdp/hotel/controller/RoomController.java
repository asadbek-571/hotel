package uz.pdp.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.model.RoomDto;
import uz.pdp.hotel.model.res.ApiResponse;
import uz.pdp.hotel.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService service;



    @PostMapping("/save")
    public ResponseEntity<ApiResponse<RoomDto>> save(@RequestBody RoomDto dto ){
        return service.save(dto);
    }


    @GetMapping("/getPage/{id}")
    public ResponseEntity<ApiResponse<List<Room>>> getPage(@PathVariable(value = "id") Long id,
                                                           @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                                                           @RequestParam(value = "size",defaultValue = "5",required = false) Integer size) {
        return service.getPage(page, size, id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<RoomDto>> update(@RequestBody RoomDto dto,@PathVariable(value = "id")Long id){
        return service.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Room>> get(@PathVariable(value = "id")Long id){
        return service.get(id);
    }

}
