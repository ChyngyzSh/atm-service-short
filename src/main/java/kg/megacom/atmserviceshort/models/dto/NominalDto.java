package kg.megacom.atmserviceshort.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class NominalDto {
    @JsonIgnore
    Long id;
    double nominal;
    double amount;
}
