public class NBody{
    public static double readRadius(String FileName){
        In in=new In(FileName);
        int number=in.readInt();
        double Radius=in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String FileName){
        In in=new In(FileName);
        int number=in.readInt();
        Planet[]Objects=new Planet[number];
        double Radius=in.readDouble();
        for(int i=0;i<number;i++){
            double xP=in.readDouble();
            double yP=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            Objects[i]=new Planet(xP,yP,xV,yV,m,img);
        }
        return Objects;
    }

    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String FileName=args[2];
        double Radius=readRadius(FileName);
        int length= readPlanets(FileName).length;
        Planet[] Objects=new Planet[length];
        System.arraycopy(readPlanets(FileName),0,Objects,0,length);
        double time;

        /*String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Radius,Radius);
        StdDraw.clear();
        StdDraw.picture(0,0,imageToDraw);
        for(Body singleObject: Objects){
            singleObject.draw();
        }
        StdDraw.show();
        StdDraw.pause(2000);*/
        for(time=0;time<T;time=time+dt){
            double[] Xforces=new double[length];
            double[] Yforces=new double[length];
            for(int i=0;i<length;i++){
                Xforces[i]=Objects[i].calcNetForceExertedByX(Objects);
                Yforces[i]=Objects[i].calcNetForceExertedByY(Objects);
            }
            for(int i=0;i<length;i++){
                Objects[i].update(dt,Xforces[i],Yforces[i]);
            }

            String imageToDraw = "images/starfield.jpg";
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-Radius,Radius);
            StdDraw.clear();
            StdDraw.picture(0,0,imageToDraw);
            for(Planet singleObject: Objects){
                singleObject.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }
        
        StdOut.printf("%d\n", length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Objects[i].xxPos, Objects[i].yyPos, Objects[i].xxVel,
                    Objects[i].yyVel, Objects[i].mass, Objects[i].imgFileName);
        }
    }
}