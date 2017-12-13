package dwightvdv.simplecounter;

/**
 * Created by dwight on 1/12/2017.
 */


public class Counter {
    /** Vars */
    private int count;
    private int goal;
    private int step;
    private String name;

    /** Getters */
    public int getCount()   {return this.count;}
    public int getGoal()    {return this.goal;}
    public String getName() {return this.name;}

    /** Setters */
    public void setGoal(int newGoal)    {this.goal = newGoal;}
    public void setName(String newName) {this.name = newName;}
    public void setCount(int newCount)  {this.count = newCount;}

    /** Constructors */
    public Counter() {
        count = 0;
        goal = 100;
        step = 1;
        name = "Counter";
    }

    /** Methods */
    public void increment() {this.count +=  this.step;}
    public void revert()    {this.count -= this.step;}
    public void reset()     {this.count = 0;}
    public boolean isGoal() {return this.count == this.goal;}
}
