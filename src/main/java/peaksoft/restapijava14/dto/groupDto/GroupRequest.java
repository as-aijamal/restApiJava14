package peaksoft.restapijava14.dto.groupDto;

import lombok.Builder;

@Builder
public record GroupRequest(
        String groupName,
        String imageLink,
        String description
) {
}
