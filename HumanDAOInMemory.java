public class HumanDAOInMemory implements IHumanDAO{
    private static int count_id=0; //max index value+1

    private static Human[] humans=new Human[10];

    public Human create(Human human) throws Exception{
        if(count_id < humans.length){
            human.setId(count_id);
            humans[count_id] = human;
            count_id++;
            return human;
        }else{
            boolean found=false;
            for(int i=0; i<humans.length; i++){
                if(humans[i]==null){
                    found=true;
                    humans[i] = human;
                    return human;     
                }
            }
            throw new Exception("unable to store the record!");
        }
    }

    public Human findById(int id){
        return humans[id];
    }

    public void update(Human human){
        humans[human.getId()] = human;	
    }

    public void delete(int id){
        if(id>=0 && id<humans.length){ 
            humans[id]=null;
            //kko:
            if(id==count_id-1)  count_id--;
        }
    }
}