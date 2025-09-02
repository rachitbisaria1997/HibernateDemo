package entities;

public class ThreadExam extends Thread{

    public void run(){

        for(int i=1; i <=10; i++){
            System.out.print(i + " ");

            System.out.println();
        }

    }

    public static void main(String[] args) {
        ThreadExam obj = new ThreadExam();
        obj.start();

        obj.start();

    }

}
