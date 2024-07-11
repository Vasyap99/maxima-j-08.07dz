public class MemSequenceGenerator implements IIdGenerator{
    private int id=0;
    
    @Override
    public int generateId(){
        return id++;
    }
}