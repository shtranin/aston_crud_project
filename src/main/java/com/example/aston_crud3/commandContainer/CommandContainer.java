package com.example.aston_crud3.commandContainer;

import com.example.aston_crud3.commandContainer.commands.*;

import java.util.HashMap;

public class CommandContainer {

    private final HashMap<String, Command> container;
    private static final CommandContainer instance = new CommandContainer();
    private final SelectUsersCommand defaultCommand = new SelectUsersCommand();

    {container = new HashMap<>();
        container.put("/create_user",new CreateUserCommand());
        container.put("/delete_user",new DeleteUserCommand());
        container.put("/edit_user",new EditUserCommand());
        container.put("/insert_user",new InsertNewUserCommand());
        container.put("/groups",new SelectGroupsCommand());
        container.put("/list",new SelectUsersCommand());
        container.put("/update_user",new UpdateUserCommand());
        container.put("/user_groups", new UserGroupsCommand());
        container.put("/delete_group",new DeleteGroupCommand());
        container.put("/subscribe_menu",new SubscribeMenuCommand());
        container.put("/create_group",new CreateGroupCommand());
        container.put("/insert_group",new InsertNewGroupCommand());
        container.put("/subscribe_group",new SubscribeUserAtGroupCommand());

    }


    private CommandContainer() {

    }
    public static CommandContainer getInstance(){
        return instance;
    }
    public Command receiveCommand(String str){
        return container.getOrDefault(str,defaultCommand);
    }


    }