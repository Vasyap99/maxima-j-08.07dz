public class HumanDAOInMemorySimple implements IHumanDAO{
    private Human[] humans;

    public HumanDAOInMemorySimple(final int MAX){
        humans=new Human[MAX];
    }

    public Human create(Human human) throws Exception{
        if(findById(human.getId())!=null) return null;
        humans[human.getId()] = human;
        return human;
    }

    public Human findById(int id){
        try{
            return humans[id];
        }catch(Exception e){return null;}
    }

    public void update(Human human){
        try{
            humans[human.getId()]=human;
        }catch(Exception e){}
    }

    public void delete(int id){
        try{
            humans[id]=null;
        }catch(Exception e){}
    }

}