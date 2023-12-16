package com.example.exams.Services;

import com.example.exams.Model.Data.db.Group;
import com.example.exams.Repositories.Db.GroupEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsService {
    @Autowired
    GroupEntityRepository groupsRepository;

    public List<Group> getAllGroups() {
        return groupsRepository.findAll();
    }

    public Group getGroupByGroupId(Integer groupId) {
        return groupsRepository.findGroupById(groupId);
    }
}
