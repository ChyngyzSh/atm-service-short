package kg.megacom.atmserviceshort.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nominals")
public class Nominal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int nominal;
    int amount;
}
