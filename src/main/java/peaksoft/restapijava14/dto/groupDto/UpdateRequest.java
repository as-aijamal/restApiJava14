package peaksoft.restapijava14.dto.groupDto;

import lombok.Builder;

@Builder
public record UpdateRequest(
        String groupName,
        String description
) {
}
