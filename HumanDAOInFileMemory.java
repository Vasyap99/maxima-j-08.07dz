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

    private void writeRec(int offset,Human human) throws Exception{
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
    }

    public Human create(Human human) throws Exception{
        if(findById(human.getId())!=null) return null; 	//!!prinimaem Id - pervichnyj kl'uch

        long L=raf.length();
        int offset=0;

        while(offset<raf.length()){
            raf.seek(offset);
            if(raf.readByte()!='T'){
                writeRec(offset,human);
                return human;
            }
            offset+=rec_len;
        }

        //add extra space to file
        raf.seek(raf.length());
        for(int i=0;i<rec_len;i++){
            raf.writeByte(0);
        }
        offset=(int)L;
        writeRec(offset,human);

        return human;
    }

    public Human findById(int id){
        try{        

        int offset=0;
        while(offset<raf.length()){
            raf.seek(offset);                
            if(raf.readByte()=='T'){ //Eto zapis' v bd
                raf.seek(offset+1+4+sl[0]+4+sl[1]+4+sl[2]  +4);                
                int id1=raf.readInt();
                if(id1==id){  //Eto nuzhnaja zapis'
                    raf.seek(offset+1+4+sl[0]+4+sl[1]+4+sl[2]  );                
                    int age=raf.readInt();         
                    //
                    raf.seek(offset+1);
                    int fnL=raf.readInt();           
                    char[] fnc=new char[fnL];
                    for(int i=0;i<fnL;i++){
                        fnc[i]=raf.readChar();
                    }      
                    String fn=new String(fnc);     
                    //
                    raf.seek(offset+1+4+sl[0]);
                    int lnL=raf.readInt();
                    char[] lnc=new char[lnL];
                    for(int i=0;i<lnL;i++){
                        lnc[i]=raf.readChar();
                    }      
                    String ln=new String(lnc);     
                    //
                    raf.seek(offset+1+4+sl[0]+4+sl[1]);
                    int ptL=raf.readInt();
                    char[] ptc=new char[ptL];
                    for(int i=0;i<ptL;i++){
                        ptc[i]=raf.readChar();
                    }      
                    //
                    String pt=new String(ptc);     
                    //
                    return new Human(fn,ln,pt,age,id);
                }
            }
            offset+=rec_len;
        }

        }catch(Exception e){System.out.println("-findId exc");}

        return null;
    }

    public void update(Human human){
        try{        

        int offset=0;
        while(offset<raf.length()){
            raf.seek(offset);                
            if(raf.readByte()=='T'){ //Eto zapis' v bd
                raf.seek(offset+1+4+sl[0]+4+sl[1]+4+sl[2]  +4);                
                int id1=raf.readInt();
                if(id1==human.getId()){  //Eto nuzhnaja zapis'
                    writeRec(offset,human);
                }
            }
            offset+=rec_len;
        }

        }catch(Exception e){System.out.println("-findId exc");}
   
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
            Human hh=null;
            t.create(hh=new Human("Вася","Пупкин","Сергеевич",41,2));

            Human h1=t.findById(3);
            System.out.println(h1);

            System.out.println(t.findById(1));
            System.out.println(t.findById(2));
            System.out.println(t.findById(3));
            System.out.println(t.findById(10));

            hh.setFirstName("Василий");
            hh.setAge(40);
            t.update(hh);
            System.out.println(t.findById(2));

            t.close();
        }catch(Exception e){System.out.println("err in main");}
    }

}
