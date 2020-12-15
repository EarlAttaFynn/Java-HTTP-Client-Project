package views;

import models.Id;

public class IdTextView {
    Id idToDisplay;
    public IdTextView(Id idToDisplay) {
        this.idToDisplay = idToDisplay;
    }
    @Override
    public String toString() {
        return String.format("\nUserid: %s\nName: %s\nGithub: %s\n\n", idToDisplay.getUserid(), idToDisplay.getName(), idToDisplay.getGithub());
    }
}