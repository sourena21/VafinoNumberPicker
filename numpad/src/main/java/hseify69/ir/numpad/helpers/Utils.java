package hseify69.ir.numpad.helpers;

public class Utils {

    public static String getStandardMobileFormat(String cell) {
        if (cell.length() == 11 && cell.substring(0, 2).equals("09")) {
            return cell;
        } else if (cell.length() == 13 && cell.substring(0, 4).equals("+989")) {
            return "0" + cell.substring(3, cell.length());
        } else if (cell.length() == 12 && cell.substring(0, 3).equals("989")) {
            return "0" + cell.substring(2, cell.length());
        } else if (cell.length() == 14 && cell.substring(0, 5).equals("00989")) {
            return "0" + cell.substring(4, cell.length());
        } else if (cell.length() == 10 && cell.substring(0, 1).equals("9")) {
            return "0" + cell;
        } else {
            return null;
        }
    }

    public static boolean isEnteredCellNumber(String cell) {
        if (cell.length() == 11) {
            if (cell.substring(0, 2).equals("09")) {
                return true;
            } else {
                return false;
            }
        } else if (cell.length() == 12) {
            if (cell.substring(0, 3).equals("989")) {
                return true;
            } else {
                return false;
            }
        } else if (cell.length() == 14) {
            if (cell.substring(0, 5).equals("00989")) {
                return true;
            } else {
                return false;
            }
        } else if (cell.length() == 10) {
            if (cell.substring(0, 1).equals("9")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
