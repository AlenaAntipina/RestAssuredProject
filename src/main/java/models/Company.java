package models;

import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Company implements Serializable {
    private String name;
    private String catchPhrase;
    private String bs;
}
