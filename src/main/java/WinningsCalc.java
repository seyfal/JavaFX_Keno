public class WinningsCalc{

    public static double calculateWinnings(int[] pickedNumbers, int[] drawnNumbers) {
        int numMatches = countMatches(pickedNumbers, drawnNumbers);

        switch (pickedNumbers.length) {

            case 1:
                return numMatches == 1 ? 2.0 : 0.0;
            case 2:
                return numMatches == 2 ? 11.0 : 0.0;
            case 4:
                switch (numMatches) {
                    case 4:
                        return 75.0;
                    case 3:
                        return 5.0;
                    case 2:
                        return 1.0;
                    default:
                        return 0.0;
                }
            case 8:
                switch (numMatches) {
                    case 8:
                        return 10000.0;
                    case 7:
                        return 750.0;
                    case 6:
                        return 50.0;
                    case 5:
                        return 12.0;
                    case 4:
                        return 2.0;
                    default:
                        return 0.0;
                }
            default:
                return 0.0;
        }
    }

    private static int countMatches(int[] a, int[] b) {
        int count = 0;
        for (int k : a) {
            for (int i : b) {
                if (k == i) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}


