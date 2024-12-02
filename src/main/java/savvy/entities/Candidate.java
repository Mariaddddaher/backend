package savvy.entities;

import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String address;

    @Column
    private PDFDocument pdfDocument;
    }

