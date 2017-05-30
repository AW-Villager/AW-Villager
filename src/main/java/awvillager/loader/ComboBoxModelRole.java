package awvillager.loader;

import org.aiwolf.common.data.Role;

public class ComboBoxModelRole {

    private Role role;
    private String name;

    public ComboBoxModelRole(Role role,String name){
        this.role = role;
        this.name = name;
    }

    public Role getRole(){
        return  this.role;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
