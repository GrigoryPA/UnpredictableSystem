import java.awt.*;

public class MainFrame {
    public static int N_GEN=5;

    public static void main(String[] args) {
        Display D = new Display();
        Life life1=new Life(D);
        D.CreateAndOpenImage();
        for(int i=0;i<N_GEN;i++){
            try {
                Thread.sleep(2000);
                life1.Iteration();
                D.UpdateImage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
