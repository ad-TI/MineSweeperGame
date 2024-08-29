public class Space {
    private boolean isMine;
    private boolean flagged;
    private boolean isRevealed;
    private int numMineNear;

    /**
     * The Space() constructor initializes the instance variables isMine, flagged
     * and isRevealed when
     * a new Space object is created
     */
    public Space() {
        this.isMine = false;
        flagged = false;
        isRevealed = false;
    }

    /**
     * The setFlagged method sets a boolean variable `flagged` to a specified value.
     * 
     * @param flagged a boolean variable that indicates whether an item has
     *                been flagged or not. It is used to set the flagged status of
     *                an object to either true or false.
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * The reveal method sets the `isRevealed` boolean variable to true.
     */
    public void reveal() {
        this.isRevealed = true;
    }

    /**
     * The hasMine() method returns a boolean value indicating whether the object
     * has a mine.
     * 
     * @return The method returns a boolean value indicating whether the object has
     *         a mine
     *         or not.
     */
    public boolean hasMine() {
        return this.isMine;
    }

    /**
     * The setMine method sets the boolean variable isMine to indicate whether a
     * mine is present.
     * 
     * @param b a boolean variable that indicates whether
     *          a mine is set or not
     */
    public void setMine(boolean b) {
        this.isMine = b;
    }

    /**
     * The setRevealed method sets the value of the isRevealed boolean variable.
     * 
     * @param b a boolean variable that indicates
     *          whether the object should be revealed or not
     */
    public void setRevealed(boolean b) {
        this.isRevealed = b;
    }

    /**
     * The isRevealed() method returns the value of the isRevealed boolean variable.
     * 
     * @return the value of the isRevealed instance variable.
     */
    public boolean isRevealed() {
        return this.isRevealed;
    }

    /**
     * The method isFlagged() returns the value of a boolean variable flagged.
     * 
     * @return the value of the boolean variable flagged.
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * This Java function sets the number of mines near a specific location.
     * 
     * @param b represents the number of mines near a
     *          particular location on a game board.
     */
    public void setMineNear(int b) {
        this.numMineNear = b;
    }

    /**
     * The getNumMineNear method returns the number of mines near a particular
     * location.
     * 
     * @return the value of the `numMineNear` instance
     *         variable.
     */
    public int getNumMineNear() {
        return this.numMineNear;
    }

}
