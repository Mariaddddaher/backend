package savvy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PDFDocument {
    private String fileName;
    private Binary content;
}
