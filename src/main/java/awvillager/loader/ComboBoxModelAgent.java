package awvillager.loader;

import org.aiwolf.common.data.Player;

public class ComboBoxModelAgent {

    String title;
    Class<? extends Player> agentClass;

    Player instance;

    public ComboBoxModelAgent(String title, Class<? extends Player> agentClass){
        this.title = title;
        this.agentClass = agentClass;
        try {
            this.instance = agentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Class<? extends Player> getAgentClass(){
        return this.agentClass;
    }

    public Player getPlayerInstance(){
        return this.instance;
    }

    @Override
    public String toString(){
        return title;
    }

}
