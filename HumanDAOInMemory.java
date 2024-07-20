public class HumanDAOInMemory implements IHumanDAO{
    private static int count_id=0; //max index value+1

    private static Human[] humans=new Human[10];

    @Override
    public Human create(Human human) throws Exception{
        if(findById(human.getId())!=null) return null;
        if(count_id < humans.length){
            humans[count_id++] = human;
            return human;
        }else{
            boolean found=false;
            for(int i=0; i<count_id; i++){
                if(humans[i]==null){
                    found=true;
                    humans[i] = human;
                    return human;     
                }
            }
            throw new Exception("unable to store the record!");
        }
    }

    @Override
    public Human findById(int id){
        for(int i=0; i<count_id; i++){
            if(humans[i]!=null && humans[i].getId()==id) return humans[i];
        }
        return null;
    }

    @Override
    public void update(Human human){
        int id=human.getId();
        for(int i=0; i<count_id; i++){
            if(humans[i]!=null && humans[i].getId()==id){ //nashli
                humans[i] = human;	
            }
        }
    }

    @Override
    public void delete(int id){
        for(int i=0; i<count_id; i++){
            if(humans[i]!=null && humans[i].getId()==id){ //nashli
                humans[i]=null;
                //kko:
                if(i==count_id-1)  count_id--;
                break;
            }
        }
    }

}