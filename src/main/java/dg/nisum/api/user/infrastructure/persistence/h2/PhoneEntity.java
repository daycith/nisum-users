package dg.nisum.api.user.infrastructure.persistence.h2;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PhoneEntity {
    private String number;
    private String cityCode;

    private String countryCode;
}
