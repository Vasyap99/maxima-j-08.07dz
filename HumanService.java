public class HumanService implements IHumanService{
    private IHumanDAO humanDAO;
    private IIdGenerator iig;

    public HumanService(IHumanDAO humanDAO,IIdGenerator iig){
        this.humanDAO=humanDAO;
        this.iig=iig;
    }

    @Override
    public Human create(Human human) throws Exception{
        if(human.getAge()>0){
            human.setId(iig.generateId());
            return humanDAO.create(human);
        }else throw new IllegalArgumentException("Wrong age!");
    }

    @Override
    public Human findById(int id){
        return humanDAO.findById(id);
    }

    @Override
    public void update(Human human){
        humanDAO.update(human);
    }

    @Override
    public void delete(int id){
        humanDAO.delete(id);
    }
}
