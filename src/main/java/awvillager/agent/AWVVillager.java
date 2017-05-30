package awvillager.agent;

import org.aiwolf.sample.player.SampleVillager;

import awvillager.player.IPrayerStatus;

public class AWVVillager extends SampleVillager implements IPrayerStatus{

    @Override
    public String getName() {
        return "AWサンプル";
    }

}
