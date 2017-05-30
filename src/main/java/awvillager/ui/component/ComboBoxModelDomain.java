package awvillager.ui.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.aiwolf.common.data.Player;

import awvillager.loader.AgentLoader;
import awvillager.loader.ComboBoxModelAgent;

public class ComboBoxModelDomain {

    String title;

    List<ComboBoxModelAgent> list = new ArrayList<>();

    AgentLoader loader;

    public ComboBoxModelDomain(String title){
        this.title = title;
    }

    public void setAgentLoader(AgentLoader loader){
        this.loader = loader;
    }

    public void initLoader(){



        Set<Class<? extends Player>> modules =loader.loadClass();

        for(Class<? extends Player> c:modules){

            String name = "none";

            try {
                Player p = c.newInstance();
                if(p.getName()!=null){
                    name = p.getName();
                }
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }

            ComboBoxModelAgent ca = new ComboBoxModelAgent(name + " (" +c.getSimpleName()+".class)",c);
            list.add(ca);

        }

    }

    public List<ComboBoxModelAgent> getAgentList(){
        return this.list;
    }

    @Override
    public String toString(){
        return title;
    }

}
