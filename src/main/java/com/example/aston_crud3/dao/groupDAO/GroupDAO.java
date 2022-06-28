package com.example.aston_crud3.dao.groupDAO;

import com.example.aston_crud3.dao.DAO;
import com.example.aston_crud3.model.group.Group;

import java.util.List;

public interface GroupDAO extends DAO<Group> {
    List<Group> getAllByUserId(Long userId);
    List<Group> getNotSubscribedGroupsByUserId(Long userId);
}
