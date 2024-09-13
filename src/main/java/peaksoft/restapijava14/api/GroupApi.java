package peaksoft.restapijava14.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restapijava14.dto.SimpleResponse;
import peaksoft.restapijava14.dto.groupDto.GroupRequest;
import peaksoft.restapijava14.dto.groupDto.GroupResponse;
import peaksoft.restapijava14.dto.groupDto.SearchResponse;
import peaksoft.restapijava14.dto.groupDto.UpdateRequest;
import peaksoft.restapijava14.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("api/groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    @PostMapping()
    public SimpleResponse saveGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(groupRequest);
    }

    @GetMapping
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroup();
    }

    @GetMapping("/{id}")
    public GroupResponse getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse updateGroup(@PathVariable Long id,
                                      @RequestBody UpdateRequest updateRequest) {
        return groupService.updateGroup(id, updateRequest);

    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteGroup(@PathVariable Long id){
        return groupService.deleteGroup(id);
    }


    @PostMapping("/{studentId}/{groupId}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long studentId,
                                               @PathVariable Long groupId){
        return groupService.assignStudentToGroup(studentId,groupId);
    }

    @GetMapping("/search")
    public List<SearchResponse> searchGroup(@RequestParam String word){
        return groupService.searchGroup(word);
    }
}
