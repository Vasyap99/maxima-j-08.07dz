public class HumanService implements IHumanService{
    private IHumanDAO humanDAO;

    public HumanService(IHumanDAO humanDAO){
        this.humanDAO=humanDAO;
    }

    @Override
    public Human create(Human human) throws Exception{
        if(human.getAge()>0){
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
