package fr.l3alr0g.spinel.elements.gui;

import fr.l3alr0g.spinel.exceptions.IncorrectChildTypeException;
import fr.l3alr0g.spinel.elements.Node;

import imgui.ImGui;

public class GuiLayer extends GuiNode {

    /**
     * Create a new GuiLayer object with a default name.
     */
    public GuiLayer() {
        name = this.getClass().getSimpleName();
    }

    /**
     * Create a new GuiLayer object with a chosen name.
     * 
     * @param name the given name
     */
    public GuiLayer(String name) {
        this.name = name;
    }

    /**
     * Draw the Layer and its children.
     */
    @Override
    public void draw() {
        ImGui.begin(name);
        compute();
        ImGui.end();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compatibilityCheck(Node child) {
        if (!(child instanceof GuiNode)) {
            throw new IncorrectChildTypeException(
                    "GuiLayer element can only receive children of types GuiNode or lower.");
        }
    }
}
