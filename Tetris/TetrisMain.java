import java.util.*;
import java.io.*;
class Board
{
    public static char board[][]=new char[20][20];
    public void generateBoard()
    {
        int i,j;
        for(i=0;i<20;i++)
        {
            for(j=0;j<20;j++)
            {
                if(j==0 || j==19 || i==0 || i==19)
                {
                    board[i][j]='*';
                }
                
            }
        }
    }
    void displayBoard()
    {
        int i,j;
        for(i=0;i<20;i++)
        {
            for(j=0;j<20;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

}


abstract class Shape 
{
    int x,y;
    int version=1;
     int coords[][] = new int[4][2];
    void changeBoard()
   {
      //  generateBoard();  
        Board.board[coords[0][0]][coords[0][1]]='#';
       Board.board[coords[1][0]][coords[1][1]]='#';
       Board.board[coords[2][0]][coords[2][1]]='#';
       Board.board[coords[3][0]][coords[3][1]]='#';
   }
   void rem_prev_shape()
   {
    Board.board[coords[0][0]][coords[0][1]]=' ';
    Board.board[coords[1][0]][coords[1][1]]=' ';
    Board.board[coords[2][0]][coords[2][1]]=' ';
    Board.board[coords[3][0]][coords[3][1]]=' ';
   }

   int check_boundary(int x,int y)
   {
      if(x==19 || x==0)
          return 0;
       else if(y==19 || y==0)
           return 0;
       else
           return 1;
   }

   abstract void rotateLeft();
   abstract void rotateRight();
   abstract void generate_shape();
  
   int check_right()
   {
    int flag=0;
    for(int i=0;i<4;i++)
    {
        if(coords[i][1]==18)
        {
            flag=1;
            break;
        }
    }
    return flag;
   }

   void moveRight()
   {
       int flag=0;
       for(int i=0;i<4;i++)
       {
           if(coords[i][1]==18)
           {
               flag=1;
               break;
           }
       }
       if(flag==0)
       {
            for(int i=0;i<4;i++)
            {
               
                coords[i][1]+=1;
            }
       }
   }
    
   int check_left()
   {
    int flag=0;
    for(int i=0;i<4;i++)
    {
        if(coords[i][1]==1)
        {
            flag=1;
            break;
        }
    }
    return flag; 
   }

   void moveLeft()
   {
       int flag=0;
       for(int i=0;i<4;i++)
       {
           if(coords[i][1]==1)
           {
               flag=1;
               break;
           }
       }
       if(flag==0)
       {
            for(int i=0;i<4;i++)
            {
                coords[i][1]-=1;
            }
       }
    
   }

   int check_bottom()
   {
    
    for(int i=0;i<4;i++)
    {
        if(Board.board[coords[i][0]+1][coords[i][1]] == '#' || Board.board[coords[i][0]+1][coords[i][1]] == '*')
        {
            return 0;
            
        }
    }
    
    return 1;
   }

   void moveDown()
   {
      
      int c=check_bottom();
       if(c==1)
       {
            for(int i=0;i<4;i++)
            {
                coords[i][0]+=1;
            }
         
       }
    }

    int check_up()
    {
     
     for(int i=0;i<4;i++)
     {
         if(Board.board[coords[i][0]-1][coords[i][1]] == '#' || Board.board[coords[i][0]-1][coords[i][1]] == '*')
         {
             return 0;
             
         }
     }
     
     return 1;
    }

    void moveUp()
   {
      
      int c=check_up();
       if(c==1)
       {
            for(int i=0;i<4;i++)
            {
                coords[i][0]-=1;
            }
         
       }
    }
   
}

class Stick extends Shape
{
  
   Stick()
   {
        x=1;
        y=random_generate();
   } 
   int random_generate()
   {
       Random rand = new Random();
       int r = rand.nextInt(18);
       if(r==0)
       r=1;
       return r;
   }
   void generate_shape()
   {
       coords[0][0]=x;
       coords[0][1]=y;
       coords[1][0]=x+1;
       coords[1][1]=y;
       coords[2][0]=x+2;
       coords[2][1]=y;
       coords[3][0]=x+3;
       coords[3][1]=y;

   }

   void makeV1()
   {
       version=1;
    
       x=coords[0][0];
       y=coords[0][1];

    int flag=0;
    if(check_boundary(x, y)==1)
    {
        if(check_boundary(x+1, y)==1)
        {
            if(check_boundary(x+2, y)==1)
            {
                if(check_boundary(x+3, y)==1)
                {
                    flag=1;
                }
            }
        }
    }

    if(flag==1)
    {
    coords[0][0]=x;
    coords[0][1]=y;
    coords[1][0]=x+1;
    coords[1][1]=y;
    coords[2][0]=x+2;
    coords[2][1]=y;
    coords[3][0]=x+3;
    coords[3][1]=y;
    }

   }

   void makeV2()
   {
      version=2; 
      x=coords[0][0];
    y=coords[0][1];
   
    int flag=0;
    if(check_boundary(x+3, y)==1)
    {
        if(check_boundary(x+3, y+1)==1)
        {
            if(check_boundary(x+3, y+2)==1)
            {
                if(check_boundary(x+3, y+3)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
    coords[0][0]=x+3;
    coords[0][1]=y;
    coords[1][0]=x+3;
    coords[1][1]=y+1;
    coords[2][0]=x+3;
    coords[2][1]=y+2;
    coords[3][0]=x+3;
    coords[3][1]=y+3;
    }

   }

   void rotateRight()
   {
       if(version==1)
       {
           makeV2();
       }
       else
       {
           makeV1();
       }
   }

   void rotateLeft()
   {
       if(version==1)
       {
           makeV2();
       }
       else
       {
           makeV1();
       }
   }
}

class Square extends Shape
{
    Square()
    {
        x=1;
        y=random_generate();
    }
    int random_generate()
    {
        Random rand = new Random();
        int r = rand.nextInt(18);
        if(r==0)
        r=1;
        return r;
    }
    void generate_shape()
    {
        coords[0][0]=x;
        coords[0][1]=y;
        coords[1][0]=x;
        coords[1][1]=y+1;
        coords[2][0]=x+1;
        coords[2][1]=y;
        coords[3][0]=x+1;
        coords[3][1]=y+1;
    }
    void rotateLeft()
    {
        
    }
    void rotateRight()
    {
        
    }
}

class Lshape extends Shape
{
    Lshape()
    {
        x=1;
        y=random_generate();
    }
    int random_generate()
    {
        Random rand = new Random();
        int r = rand.nextInt(18);
        if(r==0)
        r=1;
        return r;
    }
    void generate_shape()
    {
        coords[0][0]=x;
        coords[0][1]=y;
        coords[1][0]=x+1;
        coords[1][1]=y;
        coords[2][0]=x+2;
        coords[2][1]=y;
        coords[3][0]=x+2;
        coords[3][1]=y+1;
    }
    void makeV1()
    {
        version=1;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x, y)==1)
    {
        if(check_boundary(x+1, y)==1)
        {
            if(check_boundary(x+2, y)==1)
            {
                if(check_boundary(x+2, y+1)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x;
        coords[0][1]=y;
        coords[1][0]=x+1;
        coords[1][1]=y;
        coords[2][0]=x+2;
        coords[2][1]=y;
        coords[3][0]=x+2;
        coords[3][1]=y+1;
    }

    }
    void makeV2()
    {
        version=2;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x+2, y)==1)
    {
        if(check_boundary(x+2, y+1)==1)
        {
            if(check_boundary(x+2, y+2)==1)
            {
                if(check_boundary(x+3, y)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x+2;
        coords[0][1]=y;
        coords[1][0]=x+2;
        coords[1][1]=y+1;
        coords[2][0]=x+2;
        coords[2][1]=y+2;
        coords[3][0]=x+3;
        coords[3][1]=y;
    }
}
    void makeV3()
    {
        version=3;
        x=coords[0][0];
       y=coords[0][1];

       int flag=0;
       if(check_boundary(x+2, y-1)==1)
       {
           if(check_boundary(x+2, y)==1)
           {
               if(check_boundary(x+3, y)==1)
               {
                   if(check_boundary(x+4, y)==1)
                   {
                       flag=1;
                   }
               }
           }
       }
       if(flag==1)
       {
        coords[0][0]=x+2;
        coords[0][1]=y-1;
        coords[1][0]=x+2;
        coords[1][1]=y;
        coords[2][0]=x+3;
        coords[2][1]=y;
        coords[3][0]=x+4;
        coords[3][1]=y;
    }
}
    void makeV4()
    {
        version=4;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x+1, y)==1)
    {
        if(check_boundary(x+2, y)==1)
        {
            if(check_boundary(x+2, y-1)==1)
            {
                if(check_boundary(x+2, y-2)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x+1;
        coords[0][1]=y;
        coords[1][0]=x+2;
        coords[1][1]=y;
        coords[2][0]=x+2;
        coords[2][1]=y-1;
        coords[3][0]=x+2;
        coords[3][1]=y-2;
    }
}

    void rotateRight()
    {
        if(version==1)
        {
            makeV2();
        }
       else if(version==2)
        {
            makeV3();
        }
        else if(version==3)
        {
            makeV4();
        }
        else
        {
            makeV1();
        }
    }
    void rotateLeft()
    {
        if(version==1)
        {
            makeV4();
        }
       else if(version==4)
        {
            makeV3();
        }
        else if(version==3)
        {
            makeV2();
        }
        else
        {
            makeV1();
        }
    }
}

class Zshape extends Shape
{
  
   Zshape()
   {
        x=1;
        y=random_generate();
   } 
   int random_generate()
   {
       Random rand = new Random();
       int r = rand.nextInt(17);
       if(r==0)
       r=1;
       return r;
   }
   void generate_shape()
   {
       coords[0][0]=x;
       coords[0][1]=y;
       coords[1][0]=x;
       coords[1][1]=y+1;
       coords[2][0]=x+1;
       coords[2][1]=y+1;
       coords[3][0]=x+1;
       coords[3][1]=y+2;
    }

    void makeV1()
    {
        version=1;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x, y)==1)
    {
        if(check_boundary(x, y+1)==1)
        {
            if(check_boundary(x+1, y+1)==1)
            {
                if(check_boundary(x+1, y+2)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x;
        coords[0][1]=y;
        coords[1][0]=x;
        coords[1][1]=y+1;
        coords[2][0]=x+1;
        coords[2][1]=y+1;
        coords[3][0]=x+1;
        coords[3][1]=y+2;
    }
}
    void makeV2()
    {
        version=2;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x, y+2)==1)
    {
        if(check_boundary(x+1, y+1)==1)
        {
            if(check_boundary(x+1, y+2)==1)
            {
                if(check_boundary(x+2, y+1)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x;
        coords[0][1]=y+2;
        coords[1][0]=x+1;
        coords[1][1]=y+1;
        coords[2][0]=x+1;
        coords[2][1]=y+2;
        coords[3][0]=x+2;
        coords[3][1]=y+1;
    }
}
    void makeV3()
    {
        version=3;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x+1, y)==1)
    {
        if(check_boundary(x+1, y+1)==1)
        {
            if(check_boundary(x+2, y+1)==1)
            {
                if(check_boundary(x+2, y+2)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x+1;
        coords[0][1]=y;
        coords[1][0]=x+1;
        coords[1][1]=y+1;
        coords[2][0]=x+2;
        coords[2][1]=y+1;
        coords[3][0]=x+2;
        coords[3][1]=y+2;
    }
}
    void makeV4()
    {
        version=4;
        x=coords[0][0];
    y=coords[0][1];

    int flag=0;
    if(check_boundary(x+1, y+2)==1)
    {
        if(check_boundary(x+2, y+1)==1)
        {
            if(check_boundary(x+2, y+2)==1)
            {
                if(check_boundary(x+3, y+1)==1)
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        coords[0][0]=x+1;
        coords[0][1]=y+2;
        coords[1][0]=x+2;
        coords[1][1]=y+1;
        coords[2][0]=x+2;
        coords[2][1]=y+2;
        coords[3][0]=x+3;
        coords[3][1]=y+1;
    }
}

    void rotateRight()
    {
        if(version==1)
        {
            makeV2();
        }
        else if(version==2)
        {
            makeV3();
        }
        else if(version==3)
        {
            makeV4();
        }
        else
        {
            makeV1();
        }
    }
    void rotateLeft()
    {
        if(version==1)
        {
            makeV4();
        }
       else if(version==4)
        {
            makeV3();
        }
        else if(version==3)
        {
            makeV2();
        }
        else
        {
            makeV1();
        }
    }

}

public class TetrisMain
{
    int fig;
   static int hash[]=new int[20];
   static Stack<Character> stack = new Stack<Character>();
    public final static void clearScreen() 
    {  
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(Exception e)
        {
            // Handle any exception
        }      
    }
    void random_figure()
    {
        Random rand = new Random();
         int r = rand.nextInt(4);
        fig = r;
    }

    public static void check_hash(Shape obj,Board b)
    {
        for(int i=0;i<4;i++)
        {
            hash[obj.coords[i][0]]++;
        }
      for(int i=0;i<4;i++) 
      {
        if(hash[obj.coords[i][0]] == 18)
        {
        
          for(int k = obj.coords[i][0]; k>1 ; k--)
          {
            for(int j=1;j<19;j++)
            {
              b.board[k][j] = b.board[k-1][j];
            }
          }
          for(int k=obj.coords[i][0];k>=1 && hash[k]!=0 ; k--)
          {
            hash[k] = hash[k-1];
            
          }
        }
      }
    }

    public static void main(String[] args)
    {
      Board b = new Board();
        b.generateBoard();
     
        TetrisMain t = new TetrisMain();
        t.random_figure();
        Shape ob;
        if(t.fig == 0)
        {
            ob = new Stick();

        }
        else if(t.fig ==1)
        {
             ob = new Square();
        }
        else if(t.fig==2)
        {
             ob = new Lshape();
        }
        else
        {
             ob = new Zshape();
        }
        ob.generate_shape();
        ob.changeBoard();
        b.displayBoard();
        ob.rem_prev_shape();
        Scanner sc = new Scanner(System.in);
        char ch,topch;
         ch = sc.next().charAt(0); 
        
         clearScreen();
        

        while(true)
        {
            if(ch=='a')
            {
                stack.push(ch); // pushing character in stack
                ob.moveLeft();
                ob.changeBoard();
                b.displayBoard();
                ob.rem_prev_shape();
            }
            if(ch=='d')
            {
                stack.push(ch); // pushing character in stack
                ob.moveRight();
                ob.changeBoard();
                b.displayBoard();
                ob.rem_prev_shape();
            }
            if(ch=='s')
            {
                stack.push(ch); // pushing character in stack
                ob.moveDown();
                ob.changeBoard();
                b.displayBoard();
                ob.rem_prev_shape();
            }
            if(ch=='w')
            {
                stack.push(ch); // pushing character in stack
                ob.rotateRight();
                ob.changeBoard();
                b.displayBoard();
                ob.rem_prev_shape();
            }
            if(ch=='z')
            {
                stack.push(ch); // pushing character in stack
                ob.rotateLeft();
                ob.changeBoard();
                b.displayBoard();
                ob.rem_prev_shape();
            }
            if(ch=='u')
            {
                if(stack.empty())
                {

                }
                else
                {
                topch = stack.pop();
               
                if(topch=='a')
                {
                    ob.moveRight();
                    ob.changeBoard();
                    b.displayBoard();
                    ob.rem_prev_shape();
                }
                if(topch=='d')
                {
                    //stack.push(ch); // pushing character in stack
                    ob.moveLeft();
                    ob.changeBoard();
                    b.displayBoard();
                    ob.rem_prev_shape();
                }
                if(topch=='s')
                {
                    //stack.push(ch); // pushing character in stack
                    ob.moveUp();
                    ob.changeBoard();
                    b.displayBoard();
                    ob.rem_prev_shape();
                }
                if(topch=='w')
                {
                    //stack.push(ch); // pushing character in stack
                    ob.rotateLeft();
                    ob.changeBoard();
                    b.displayBoard();
                    ob.rem_prev_shape();
                }
                if(topch=='z')
                {
                    //stack.push(ch); // pushing character in stack
                    ob.rotateRight();
                    ob.changeBoard();
                    b.displayBoard();
                    ob.rem_prev_shape();
                }
            }

            }
            if(ob.check_bottom()==0)
            {
                check_hash(ob,b);
                ob.changeBoard();
                b.displayBoard();
                clearScreen();
                t.random_figure();
                if(t.fig == 0)
                {
                    ob = new Stick();
        
                }
                else if(t.fig ==1)
                {
                     ob = new Square();
                }
                else if(t.fig==2)
                {
                     ob = new Lshape();
                }
                else
                {
                     ob = new Zshape();
                } 
                ob.generate_shape();
                ob.changeBoard();
                b.displayBoard();
                ob.rem_prev_shape();
            }
            
            ch = sc.next().charAt(0);
            
            clearScreen();   
            
        }
    }
}