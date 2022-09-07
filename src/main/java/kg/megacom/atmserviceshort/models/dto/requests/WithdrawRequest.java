package kg.megacom.atmserviceshort.models.dto.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WithdrawRequest {
    long account;
    int amount;
}
