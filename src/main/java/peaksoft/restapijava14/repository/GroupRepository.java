package peaksoft.restapijava14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.restapijava14.dto.groupDto.GroupResponse;
import peaksoft.restapijava14.dto.groupDto.SearchResponse;
import peaksoft.restapijava14.entity.Group;

import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    @Query("select new peaksoft.restapijava14.dto.groupDto.GroupResponse(g.id,g.groupName,g.imageLink,g.description) from Group g")
    List<GroupResponse> getAllGroups();

    @Query("select new peaksoft.restapijava14.dto.groupDto.GroupResponse(g.id,g.groupName,g.imageLink,g.description) from Group g where g.id= :id")
    GroupResponse getByIdGroup(Long id);


    @Query("select new peaksoft.restapijava14.dto.groupDto.SearchResponse(g.groupName) from Group g where  g.groupName =:word")
    List<SearchResponse> searchGroupsByGroupName(String word);

}
