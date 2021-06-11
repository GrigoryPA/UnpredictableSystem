import java.awt.*;

public class Life{
    public static Color IsLive = Color.WHITE;
    public static Color IsDead = Color.BLACK;
    public static int N_GEN=100;
    public static Display D;


    public static void Iteration(){
        for(int i = 0; i < D.widthImage; i++) {
            for (int j = 0; j < D.heightImage; j++) {
                D.new_display[i][j]=IsDead;
                if(D.display[i][j] == IsDead && CountLife(i,j)==3)
                    D.new_display[i][j]=IsLive;
                if(D.display[i][j] == IsLive){
                    if(CountLife(i,j)==3||CountLife(i,j)==2)
                        D.new_display[i][j]=IsLive;
                    else
                        D.new_display[i][j]=IsDead;
                }
            }
        }
    }

    public static int CountLife(int i, int j){
        int count=0;

        if(j+1<D.heightImage){
            if(D.display[i][j+1]==IsLive)
                count++;
            if(i+1<D.widthImage)
                if(D.display[i+1][j+1]==IsLive)
                    count++;
            if(i-1>=0)
                if(D.display[i-1][j+1]==IsLive)
                    count++;
        }
        if(j-1>=0){
            if(D.display[i][j-1]==IsLive)
                count++;
            if(i+1<D.widthImage)
                if(D.display[i+1][j-1]==IsLive)
                    count++;
            if(i-1>=0)
                if(D.display[i-1][j-1]==IsLive)
                    count++;
        }
        if(i+1<D.widthImage){
            if(D.display[i+1][j]==IsLive)
                count++;
        }
        if(i-1>=0){
            if(D.display[i-1][j]==IsLive)
                count++;
        }

        return count;
    }

    public static void main(String[] args) {
        D = new Display();
        D.CreateAndOpenImage();
        for(int i=0;i<N_GEN;i++){
            try {
                Thread.sleep(500);
                Iteration();
                D.UpdateImage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
