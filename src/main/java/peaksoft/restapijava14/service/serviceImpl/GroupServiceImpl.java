package peaksoft.restapijava14.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.groupDto.GroupRequest;
import peaksoft.restapijava14.dto.groupDto.GroupResponse;
import peaksoft.restapijava14.dto.groupDto.SearchResponse;
import peaksoft.restapijava14.dto.groupDto.UpdateRequest;
import peaksoft.restapijava14.entity.Group;
import peaksoft.restapijava14.entity.Student;
import peaksoft.restapijava14.repository.GroupRepository;
import peaksoft.restapijava14.repository.StudentRepository;
import peaksoft.restapijava14.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {
    private  final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    @Override
    public SimpleResponse saveGroup(GroupRequest groupRequest) {
        Group group = new Group();
        group.setGroupName(groupRequest.groupName());
        group.setImageLink(groupRequest.imageLink());
        group.setDescription(groupRequest.description());
        groupRepository.save(group);
        return SimpleResponse
                .builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public List<GroupResponse> getAllGroup() {
        return groupRepository.getAllGroups();
    }

    @Override
    public GroupResponse getById(Long id) {
        return groupRepository.getByIdGroup(id);
    }

    @Override
    public SimpleResponse updateGroup(Long id, UpdateRequest updateRequest) {
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found"));
        group.setGroupName(updateRequest.groupName());
        group.setDescription(updateRequest.description());
        groupRepository.save(group);
        return SimpleResponse
                .builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public SimpleResponse deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found"));
        groupRepository.deleteById(group.getId());
        return SimpleResponse
                .builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public SimpleResponse assignStudentToGroup(Long studentId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() ->
                new EntityNotFoundException("Not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new EntityNotFoundException("Student with id " + studentId + "not found"));
        student.setGroup(group);
        return SimpleResponse
                .builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public List<SearchResponse> searchGroup(String word) {
        return groupRepository.searchGroupsByGroupName(word);
    }
}
