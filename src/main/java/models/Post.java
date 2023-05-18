package models;

import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String id;
    private String title;
    private String body;

}
