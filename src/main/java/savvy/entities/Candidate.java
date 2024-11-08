package savvy.entities;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    private Integer rno;
    private String name;
    private Integer age;
    private String address;

    }

