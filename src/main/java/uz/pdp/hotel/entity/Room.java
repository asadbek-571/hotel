package uz.pdp.hotel.entity;

import javafx.beans.DefaultProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,name = "number")
    int number;

    @Column(nullable = false,name = "floor")
    int floor;

    @Column(nullable = false,name = "size")
    int size;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
    @Column(updatable = false, insertable = false)
    Long hotel_id;
}
