package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.GroupDTO;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.service.IGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements IGroupService {
    @Override
    public Group createGroup(GroupDTO groupDTO) {
        return null;
    }

    @Override
    public Group findGroupById(Long groupId) {
        return null;
    }

    @Override
    public GroupReq sendGroupReq(User user) {
        return null;
    }

    @Override
    public List<Group> yourGroups(User user) {
        return null;
    }

    @Override
    public List<Group> availableGroups() {
        return null;
    }
}
