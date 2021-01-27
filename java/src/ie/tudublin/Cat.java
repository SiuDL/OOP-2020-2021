package ie.tudublin;

public class Cat extends Animal
{
    public Cat(String name)
    {
        super(name);
    }

    private int numLives = 9;

    public int getLives(){
        return numLives;
    }

    public void Kill(){
        if(numLives > 0){
            System.out.println("Ouch");
            numLives--;
        }
        if(numLives == 0){
            System.out.println("Dead");
        }
    }
}