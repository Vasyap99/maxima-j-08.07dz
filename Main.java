public class Main{
    public static void main(String[]s) throws Exception{
        System.out.println("**************************");

        IHumanService service = new HumanService(new HumanDAOInFileMemory("Humans.db",50,50,50),new FileSequenceGenerator("Human.seq"));
        //IHumanService service = new HumanService(new HumanDAOInMemory());
        //IHumanService service = new HumanService(new HumanDAOInMemorySimple(100),new MemSequenceGenerator());

        Human h=new Human("Oleg","Igonin","Leopoldovich",21);
        service.create(h);

        Human h1=new Human("Petr","Ivanov","Sergeevich",45);
        service.create(h1);
        Human h2=new Human("Ivan","Sergeev","Mihailovich",23);
        service.create(h2);
        Human hh=new Human("Vasya","Pupkin","Genned'evich",41);
        service.create(hh);

        hh.setFirstName("Vasilij");
        hh.setAge(40);
        service.update(hh);
        System.out.println(service.findById(hh.getId()));


        System.out.println(service.findById(h.getId()));
    }
}