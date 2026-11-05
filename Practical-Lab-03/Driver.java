import java.util.Objects;
import java.util.Scanner;
public class Point {
    private int x,y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Point temp = (Point)obj;
        if(this.x == temp.x && this.y == temp.y){
            return true;
        }
        else {
            return false;
        }
}

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}

class Driver {
    public static void main(String[] args) {
        Point []p = new Point[5];
        Scanner sc = new Scanner(System.in);
        for(int i =0; i < p.length ; i++) {
            System.out.println("Enter elements:- ");
            p[i] = new Point(sc.nextInt() , sc.nextInt());
        }
int count = 0;
        for(int i = 0; i < p.length ; i++) {
            boolean alreadyPresent = false;

            for(int j = 0; j < i; j++) {
                if (p[i].equals(p[j])) {
                    alreadyPresent = true;
                    break;
                }
            }
            if(!alreadyPresent)
                count++;
        }
        System.out.println("Distinct: " + count);
    }
}