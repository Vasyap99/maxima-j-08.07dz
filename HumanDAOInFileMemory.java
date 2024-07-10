import java.io.RandomAccessFile;

public class HumanDAOInFileMemory implements IHumanDAO{
    private RandomAccessFile raf=null;
    private int[]sl; //dliny strok v baze v simvolah
    private final int rec_len;

    public HumanDAOInFileMemory(String fn,int... sl){
        this.sl=sl;
        rec_len=1+4*5+sl[0]+sl[1]+sl[2];
        try{
            raf=new RandomAccessFile(fn,"rw");
        }catch(Exception e){}
    }

    public Human create(Human human) throws Exception{
        long L=raf.length();
        int offset=rec_len*human.getId();

        if(offset>L){
            raf.seek(raf.length());
            for(int i=0;i<rec_len;i++){
                raf.writeByte(0);
            }
        }

        String fn=human.getFirstName();
        if(fn.length()>sl[0]) fn=fn.substring(0,sl[0]);
        //char[]fn_c=fn.toCharArray();

        raf.seek(offset);
        raf.writeByte('T');
        raf.writeInt(fn.length());
        raf.writeChars(fn);

        String ln=human.getLastName();
        if(ln.length()>sl[1]) ln=ln.substring(0,sl[1]);
        //char[]ln_c=ln.toCharArray();

        raf.seek(offset+1+4+sl[0]);
        raf.writeInt(ln.length());
        raf.writeChars(ln);

        String pt=human.getPatronimic();
        if(pt.length()>sl[2]) pt=pt.substring(0,sl[2]);
        //char[]pt_c=pt.toCharArray();

        raf.seek(offset+1+4+sl[0]+4+sl[1]);
        raf.writeInt(pt.length());
        raf.writeChars(pt);

        raf.seek(offset+1+4+sl[0]+4+sl[1]+4+sl[2]);
        raf.writeInt(human.getAge());
        raf.writeInt(human.getId());

        return human;
    }

    public Human findById(int id){
        return null;
    }

    public void update(Human human){
    
    }

    public void delete(int id){
    }


    public void close() throws Exception{
        //
        raf.close();
    }

    public static void main(String[]s){
        try{
            HumanDAOInFileMemory t=new HumanDAOInFileMemory("Humans.db",50,50,50);
            t.create(new Human("Петр","Иванов","Сергеевич",45,1));
            t.create(new Human("Егор","Семенов","Александрович",23,3));
            t.create(new Human("Вася","Пупкин","Сергеевич",41,2));
            t.close();
        }catch(Exception e){System.out.println("err in main");}
    }

}
