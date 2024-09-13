package peaksoft.restapijava14.dto.groupDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponse {
    private String name;

    public SearchResponse(String name) {
        this.name = name;
    }
}
