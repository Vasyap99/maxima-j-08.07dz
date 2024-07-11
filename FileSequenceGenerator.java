import java.io.*;

public class FileSequenceGenerator implements IIdGenerator{
    private String fn;
    
    public FileSequenceGenerator(String fn){
        this.fn=fn;

        /*FileOutputStream fos=null;
        try{
            fos=new FileOutputStream(fn); 
            new DataOutputStream(fos).writeInt(0);
        }catch(Exception e){
        }finally{
            try{
                fos.close();
            }catch(Exception e1){}
        }*/
    }

    @Override
    public int generateId(){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try{
            int id=0;
            try{
                fis=new FileInputStream(fn);
                id = new DataInputStream(fis).readInt();
                fis.close();
            }catch(Exception e2){}

            fos=new FileOutputStream(fn); 
            new DataOutputStream(fos).writeInt(id+1);
            fos.close();

            return id;
        }catch(Exception e){
        }finally{
            try{
                fis.close();
            }catch(Exception e1){}
            try{
                fos.close();
            }catch(Exception e1){}
        }   
        return -1;
    }
}