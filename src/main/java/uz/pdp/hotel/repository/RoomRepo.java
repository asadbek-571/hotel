package uz.pdp.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room,Long> {



    @Query(value = " SELECT * " +
            " FROM room " +
            " JOIN hotel h ON h.id = room.hotel_id " +
            " WHERE h.id=:hotel_id LIMIT :size OFFSET :start",nativeQuery = true)
    List<Room> findAllByHotelId(Integer size,Integer start,Long hotel_id);

}
