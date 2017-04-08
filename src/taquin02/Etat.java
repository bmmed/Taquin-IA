package taquin02;

import java.util.Comparator;

/**
 * Created by BMMed on 31/03/2017.
 */
public class Etat implements Comparable<Etat>{
    public String path ="";
    public int[][] matrix= new int[3][3];
    public  float f=0;
    public String hash="";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public Etat() {
    }
    public Etat(Etat e) {
        this.path=e.getPath();
        setMatrix(e.getMatrix());
        this.f=e.getF();
        this.hash=e.getHash();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        for(int i=0;i<3;i++)
            for (int j=0;j<3;j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
    }

    public void setCase(int i ,int j ,int val){
    this.matrix[i][j]=val;
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStringMatrix(){
        return this.matrix[0][0]+"|"+this.matrix[0][1]+"|"+this.matrix[0][2]+"|\n"+
                this.matrix[1][0]+"|"+this.matrix[1][1]+"|"+this.matrix[1][2]+"|\n"+
                this.matrix[2][0]+"|"+this.matrix[2][1]+"|"+this.matrix[2][2]+"|\n";
    }

    @Override
    public int compareTo(Etat o) {
        Etat e =(Etat)o;
        if ( (e.getF()-this.getF())>0) return -1;
        else return 1;
    }
}
