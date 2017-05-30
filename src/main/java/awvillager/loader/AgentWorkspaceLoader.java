package awvillager.loader;

import java.util.HashSet;
import java.util.Set;

import org.aiwolf.common.data.Player;
import org.reflections.Reflections;

public class AgentWorkspaceLoader extends AgentLoader{

    @Override
    public void init(){
        reflections  = new Reflections();
    }

    @Override
    public Set<Class<? extends Player>> loadClass(){

        Set<Class<? extends Player>> list = new HashSet();

        for(Class<? extends Player> c : super.loadClass()){
            if(c.getResource(c.getSimpleName() + ".class").toString().startsWith("file:")){
                list.add(c);
            }
        }

        return list;

    }

}
