import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static String direction;

    static int[][] OldMap = new int[4][4];

    public static void main(String[] args)
    {
        int[][] map = new int[4][4];
       for(int i=0;i<4;i++)
       {
           for (int j = 0;j<4;j++)
           {
               OldMap[i][j]=0;
               map[i][j] = 0;
           }
       }
       map[3][1] = 2;
       map[3][2]= 2;
        OldMap[3][1] = 2;
        OldMap[3][2]= 2;
        while (!IsEnd(map))
        {
            DrawMap(map);
            Update(map);
            Spawn(map);
        }
        DrawMap(map);

    }

    static boolean ScanKey()
    {
        Scanner scan = new Scanner(System.in);

        direction  = scan.next().toString();
        switch (direction) {
            case "w" :
                return true;
            case "s":
                return true;

            case "a":
                return true;

            case "d":
                return true;
            case "W":
                return true;
            case "D":
                return true;
            case "A":
                return true;
            case "S":
                return true;
            default:
                return false;

        }
    }
static void Update(int[][] map)
{

    if(ScanKey())
    {
        switch (direction.toLowerCase(Locale.ENGLISH)) {
            case "w" :
                for(int y=0;y<4;y++){
                    for(int j=1;j<4;j++){
                        for (int i=0;i<4;i++)
                        {
                            if(map[j][i]>0 && map[j-1][i]==0)
                            {
                                map[j-1][i] = map[j][i];
                                map[j][i]= 0;


                            } else if(map[j][i]==map[j-1][i])
                            {
                                map[j][i]= 0;
                                map[j-1][i] = map[j-1][i]*2;

                            }
                        }}}
            break;
            case "s":
                for(int y=0;y<4;y++){
                    for(int j=3;j>0;j--){
                        for (int i=0;i<4;i++)
                        {
                            if(map[j][i]==0 && map[j-1][i]>0)
                            {
                                map[j][i] = map[j-1][i];
                                map[j-1][i]= 0;


                            } else if(map[j][i]==map[j-1][i])
                            {
                                map[j-1][i]= 0;
                                map[j][i] = map[j][i]*2;


                            }
                        }}}
                break;

            case "a":for(int y=0;y<4;y++){
                for(int j=0;j<4;j++){
                for (int i=1;i<4;i++)
                {
                    if(map[j][i]>0 && map[j][i-1]==0)
                    {
                        map[j][i -1] = map[j][i];
                        map[j][i]= 0;


                    } else if(map[j][i]==map[j][i-1])
                {
                    map[j][i]= 0;
                    map[j][i-1] = map[j][i-1]*2;

                }
                }}}
                break;

            case "d":
                for(int y=0;y<4;y++){
                    for(int j=0;j<4;j++){
                        for (int i=3;i>0;i--)
                        {
                            if(map[j][i]==0 && map[j][i-1]>0)
                            {
                                map[j][i] = map[j][i-1];
                                map[j][i-1]= 0;

                            } else if(map[j][i]==map[j][i-1])
                            {
                                map[j][i-1]= 0;
                                map[j][i] = map[j][i]*2;

                            }
                        }}}
                break;

            default:


        }
    }
    else
        System.out.println("invalid key");
}
static void Spawn(int[][] map){
        int random;
    int x,y,value;
  int zerovalue=0;
    for(int i=0;i<4;i++)
    {
        for(int j=0;j<4;j++)
        {
            if(map[i][j]==0)
            {
                zerovalue++;
            }
        }
    }
    if(zerovalue!=0&&IsMoving(map))
    {
        Random rand = new Random();
        random = rand.nextInt(100);
        if (random>=90)
            value =4;
        else value=2;
        while (true)
        {
            x = rand.nextInt(4);
            y= rand.nextInt(4);
            if(map[x][y]==0)
            {
                map[x][y]=value;
                for(int i=0;i<4;i++)
                {
                    for(int j=0;j<4;j++)
                    {
                        OldMap[i][j]=map[i][j];

                    }
                }
                break;
            }
        }

    }
    else if(zerovalue==0)
        System.out.println("invalid key");
}
    static void DrawMap(int[][] map)
    {
        for(int i=0;i<20;i++)
        {
            System.out.println();
        }
        for(int i=0;i<4;i++)
        {System.out.print("\t\t\t\t\t");
            for (int j = 0;j<4;j++)
            {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
        for(int i=0;i<6;i++)
        {
            System.out.println();
        }
    }
    static boolean IsMoving(int[][] map) {
        int c = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != OldMap[i][j])
                    c++;
            }
        }
        return c > 0 ? true : false;
    }
    static boolean IsEnd(int[][] map)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if((map[i][j]==map[i+1][j]||map[i][j]==map[i][j+1])&&!IsMoving(map))
                    return false;

            }
            if(map[i][3]==map[i+1][3])
                return false;
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(map[i][j]==0)
                    return false;
            }
        }
        return true;
    }
}
