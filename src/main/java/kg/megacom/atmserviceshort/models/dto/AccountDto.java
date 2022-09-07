package kg.megacom.atmserviceshort.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.Client;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AccountDto {
    @JsonIgnore
    Long id;
    long account;
    int balance;
}
