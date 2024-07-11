public class Main{
    public static void main(String[]s) throws Exception{
        System.out.println("**************************");

        //IHumanService service = new HumanService(new HumanDAOInFileMemory("Humans.db",50,50,50));
        //IHumanService service = new HumanService(new HumanDAOInMemory());
        IHumanService service = new HumanService(new HumanDAOInMemorySimple(100));

        service.create(new Human("Oleg","Igonin","Leopoldovich",21,4));

        System.out.println(service.findById(4));
    }
}