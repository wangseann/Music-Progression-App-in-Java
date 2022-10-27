package model;

public enum TimeSignatures {
    FOUR_FOUR, THREE_FOUR, SEVEN_FOUR;

    //EFFECTS: returns time signature specified by given string, or FOUR_FOUR if string is not a time signature.
    public TimeSignatures timeSignatureFromString(String timeSignatureString) {
        if (timeSignatureString.equals("FOUR_FOUR")) {
           return TimeSignatures.FOUR_FOUR;
        } else if (timeSignatureString.equals("THREE_FOUR")) {
            return TimeSignatures.THREE_FOUR;
        } else if (timeSignatureString.equals("SEVEN_FOUR")) {
            return TimeSignatures.SEVEN_FOUR;
        } else {
            return TimeSignatures.FOUR_FOUR;
        }
    }
}
