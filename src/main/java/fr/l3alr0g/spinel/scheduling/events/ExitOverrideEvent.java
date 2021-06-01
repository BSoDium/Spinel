package fr.l3alr0g.spinel.scheduling.events;

public class ExitOverrideEvent extends Event {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitOverrideEvent;
    }

}
