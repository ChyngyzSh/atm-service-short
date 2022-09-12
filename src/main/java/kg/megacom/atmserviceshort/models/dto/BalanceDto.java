package kg.megacom.atmserviceshort.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BalanceDto {
    @JsonIgnore
    Long id;
    int balance;
}
