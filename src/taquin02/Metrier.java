package taquin02;

import java.util.*;

/**
 * Created by BMMed on 31/03/2017.
 */
public class Metrier {
    public int[] posI = {0, 0, 0, 1, 1, 1, 2, 2, 2};
    public int[] posJ = {0, 1, 2, 0, 1, 2, 0, 1, 2};

   //manhatan 01
    public int[] tabManhatan ;
    public int constManhatan = 4;

    public String  succes="012345678";


    public String Research(Etat E0,int[] man,int poid){

        tabManhatan =man.clone();
        constManhatan =poid;
        
        List<Etat> Frontiere= new ArrayList<Etat>();
        List<String> HashFrontiere= new ArrayList<String>();
        List<String> HashEtatExplore= new ArrayList<String>();

        E0.setF(Manhatan(E0));
        E0.setHash(HashMatrix(E0));
        Frontiere.add(E0);
        HashFrontiere.add(E0.getHash());

        while (!Frontiere.isEmpty())
        {

            Collections.sort(Frontiere);
            Collections.sort(HashEtatExplore);
            Collections.sort(HashFrontiere);

            Etat E1 =Frontiere.get(0);
            Frontiere.remove(0);

            if(E1.getHash().equals(this.succes)){
                String str="";
                str+=("\n F=G+H= "+E1.getF());
                str+=("\n La taille de la frontiere "+Frontiere.size());
                str+=("\n La taille des etats exploré "+HashEtatExplore.size());
                str+=("\n Le chemin depuis l'etat initial "+E1.getPath().length()+"\n");
                for(int i=0;i<E1.getPath().length();i++)str+="-  ";
                str+=("\n "+E1.getPath()+"\n");
                for(int i=0;i<E1.getPath().length();i++)str+="-  ";
                
                Frontiere.clear();
                HashEtatExplore.clear();
                HashFrontiere.clear();
                System.gc();

                return str;
            }
            else
            {
                List<Etat> resexp01=new ArrayList<Etat>();
                resexp01.addAll(Expence(E1));
                HashEtatExplore.add(E1.getHash());

                for (Etat i:resexp01){
                    if(!HashEtatExplore.contains(i.getHash()) && !HashFrontiere.contains(i.getHash()))
                    {
                        HashFrontiere.add(i.getHash());
                        Frontiere.add(i);
                    }
                }
                resexp01.clear();
            }
        }

        if (Frontiere.isEmpty())
        {
            System.out.println(" Pas de solution possible !!!");
            System.exit(-1);
        }



        return "";
    }


    public int[] DistElem(Etat e) {
        int[] res = new int[9];
        int dist = 0;

        for (int k = 0; k <= 2; k++) {
            for (int l = 0; l <= 2; l++) {
                dist = 0;
                dist += Math.abs(posI[e.getMatrix()[k][l]] - k);
                dist += Math.abs(posJ[e.getMatrix()[k][l]] - l);
                res[e.matrix[k][l]] = dist;
            }
        }
        return res;
    }

    public String HashMatrix(Etat e){
        String hash="";

        for (int k = 0; k <= 2; k++) {
            for (int l = 0; l <= 2; l++) {
                hash+=String.valueOf(e.getMatrix()[k][l]);
            }
        }
        return hash;
    }

    public float  Manhatan(Etat e) {
        float res = 0;
        int[] distElem = this.DistElem(e);

        for (int i = 0; i <= 8; i++) {
            res += tabManhatan[i] * distElem[i];
        }
        res = res / constManhatan;
        res += e.path.length();
        return res;
    }

    public ArrayList<Etat> Expence(Etat e) {
        ArrayList<Etat> resExp = new ArrayList<Etat>();
        int Gi = 0;
        int Gj = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j<3; j++) {
                if (e.matrix[i][j] == 8) {
                    Gi = i;
                    Gj = j;
                }
            }
        }



        Etat res1;
        String path=e.getPath();
        if (Gi > 0) {
            res1=new Etat(e);
            res1.setCase(Gi,Gj,res1.getMatrix()[Gi - 1][Gj]);
            res1.setCase(Gi-1,Gj,8);
            res1.setPath(path+"N");
            res1.setF(Manhatan(res1));
            res1.setHash(HashMatrix(res1));
            resExp.add(res1);
        }
         if (Gi < 2) {
             res1=new Etat(e);
             res1.setCase(Gi,Gj,res1.getMatrix()[Gi + 1][Gj]);
             res1.setCase(Gi+1,Gj,8);
             res1.setPath(path+"S");
             res1.setF(Manhatan(res1));
             res1.setHash(HashMatrix(res1));
             resExp.add(res1);
         }

         if (Gj < 2) {
             res1=new Etat(e);
             res1.setCase(Gi,Gj,res1.getMatrix()[Gi][Gj+1]);
             res1.setCase(Gi,Gj+1,8);
             res1.setPath(path+"E");
             res1.setF(Manhatan(res1));
             res1.setHash(HashMatrix(res1));
             resExp.add(res1);
        }

         if (Gj > 0) {
             res1=new Etat(e);
             res1.setCase(Gi,Gj,res1.getMatrix()[Gi][Gj-1]);
             res1.setCase(Gi,Gj-1,8);
             res1.setPath(path+"O");
             res1.setF(Manhatan(res1));
             res1.setHash(HashMatrix(res1));
             resExp.add(res1);
        }

        return resExp;
    }

}
