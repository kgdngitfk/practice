/**
 * Created by User on 2017/10/16.
 */
public class Start {
    public static void main(String[] args) {
        System.out.println( Start.class.getName());
        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i=0;
                    while (true){
                        if(true) i++;
                        else ;
                    }
                }
            }).start();
        }


    }
}
