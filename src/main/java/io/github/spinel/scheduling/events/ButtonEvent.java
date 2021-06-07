package io.github.spinel.scheduling.events;

public class ButtonEvent extends Event {
    private int button;

    /**
     * Create a new Button event.
     * 
     * @param button the button keycode
     */
    public ButtonEvent(int button) {
        this.button = button;
    }

    public int getButton() {
        return button;
    }

    @Override
    public boolean isActive() {
        return windowInstance.getInput().isButtonDown(button);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + button;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ButtonEvent other = (ButtonEvent) obj;
        if (button != other.button)
            return false;
        return true;
    }

}
