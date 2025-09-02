package entities;

public class Sample extends Thread{

    public void run(){

        for(int i=1; i<=10; i++){
            System.out.println(i);
        }

    }

   public static void main(String[] args){

        Sample obj = new Sample();
        obj.start();

   }
}
