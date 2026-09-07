package PatternWise.Geometry;

public class IsValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        int distanceP1 = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
        int distanceP2 = (p2[0] - p3[0]) * (p2[0] - p3[0]) + (p2[1] - p3[1]) * (p2[1] - p3[1]);
        int distanceP3 = (p3[0] - p4[0]) * (p3[0] - p4[0]) + (p3[1] - p4[1]) * (p3[1] - p4[1]);
        int distanceP4 = (p4[0] - p1[0]) * (p4[0] - p1[0]) + (p4[1] - p1[1]) * (p4[1] - p1[1]);
        int distanceP5 = (p4[0] - p2[0]) * (p4[0] - p2[0]) + (p4[1] - p2[1]) * (p4[1] - p2[1]);
        int distanceP6 = (p1[0] - p3[0]) * (p1[0] - p3[0]) + (p1[1] - p3[1]) * (p1[1] - p3[1]);

        if (distanceP5 == distanceP1 + distanceP2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int[] p1 = { 0, 0 };
        int[] p2 = { 1, 0 };
        int[] p3 = { 1, 1 };
        int[] p4 = { 0, 1 };
        IsValidSquare obj = new IsValidSquare();
        System.out.println(obj.validSquare(p1, p2, p3, p4));
    }

}
