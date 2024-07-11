public interface IHumanService{
    Human create(Human human) throws Exception;

    Human findById(int id);

    void update(Human human);

    void delete(int id);
}