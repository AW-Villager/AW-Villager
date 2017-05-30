package awvillager.loader;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.aiwolf.common.data.Player;
import org.reflections.Reflections;

public class AgentLoader {

    Reflections reflections;

    public AgentLoader(){
        this.init();
    }

    public void init(){
        reflections  = new Reflections("org.aiwolf.sample.player");
    }

    public Set<Class<? extends Player>> loadClass(){

        Set<Class<? extends Player>> list = new HashSet();

        for(Class<? extends Player> c:reflections.getSubTypesOf(Player.class)){

            if(!Modifier.isAbstract( c.getModifiers() )){
                list.add(c);
            }

        }


        return list;

        /*
        Set<Class<? extends Player>> modules =
                reflections.getSubTypesOf(Player.class);

        for(Class<? extends Player> c:modules){

            ComboBoxModelAgent ca = new  ComboBoxModelAgent(c.getName());

        }
        */

    }

}
