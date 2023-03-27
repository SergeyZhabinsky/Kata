public class Except extends Exception{
    public Except(String s, Throwable err){
        super(s, err);

        System.out.println(s);

        if(err != null)
            System.out.println(err);
    }
}
