import java.awt.*;

public class Life {
    public Display D;
    public static Color IsLive = Color.WHITE;
    public static Color IsDead = Color.BLACK;

    public Life(Display display){
        D=display;
    }

    public void Iteration(){
        for(int i=0;i<D.widthImage;i++) {
            for (int j = 0; j < D.heightImage; j++) {
                if(D.image.getRGB(i,j) ==IsDead.getRGB() && CountLife(i,j)==3)
                    D.display[i][j]=IsLive;
                if(D.image.getRGB(i,j) ==IsLive.getRGB() && CountLife(i,j)!=3)
                    D.display[i][j]=IsDead;
            }
        }
    }

    public int CountLife(int i, int j){
        int count=0;

        if(j+1<D.heightImage){
            if(D.image.getRGB(i,j+1)==IsLive.getRGB())
                count++;
            if(i+1<D.widthImage)
                if(D.image.getRGB(i+1,j+1)==IsLive.getRGB())
                    count++;
            if(i-1>=0)
                if(D.image.getRGB(i-1,j+1)==IsLive.getRGB())
                    count++;
        }
        if(j-1>=0){
            if(D.image.getRGB(i,j-1)==IsLive.getRGB())
                count++;
            if(i+1<D.widthImage)
                if(D.image.getRGB(i+1,j-1)==IsLive.getRGB())
                    count++;
            if(i-1>=0)
                if(D.image.getRGB(i-1,j-1)==IsLive.getRGB())
                    count++;
        }
        if(i+1<D.widthImage){
            if(D.image.getRGB(i+1,j)==IsLive.getRGB())
                count++;
        }
        if(i-1>=0){
            if(D.image.getRGB(i-1,j)==IsLive.getRGB())
                count++;
        }

        return count;
    }
}
