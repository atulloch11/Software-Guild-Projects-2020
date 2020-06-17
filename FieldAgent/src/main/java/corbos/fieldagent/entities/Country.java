package corbos.fieldagent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Country {

    @Id
    @Column(name = "country_code")
    private String countryCode;
    
    @Column(nullable = false)
    private String name;

}
