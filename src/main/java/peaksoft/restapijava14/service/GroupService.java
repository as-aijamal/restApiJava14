package peaksoft.restapijava14.service;

import peaksoft.restapijava14.dto.groupDto.GroupRequest;
import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.groupDto.GroupResponse;
import peaksoft.restapijava14.dto.groupDto.SearchResponse;
import peaksoft.restapijava14.dto.groupDto.UpdateRequest;

import java.util.List;

public interface GroupService {
    SimpleResponse saveGroup(GroupRequest groupRequest);
    List<GroupResponse> getAllGroup();
    GroupResponse getById(Long id);
    SimpleResponse updateGroup(Long id,UpdateRequest updateRequest);
    SimpleResponse deleteGroup(Long id);
    SimpleResponse assignStudentToGroup(Long studentId,Long groupId);
    List<SearchResponse> searchGroup(String word);
}
