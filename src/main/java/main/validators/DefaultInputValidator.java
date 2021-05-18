package main.validators;

public class DefaultInputValidator implements InputValidator{

    @Override
    public boolean validateRoomCounts(int n, int k) {
        return this.validateN(n) && this.validateK(k);
    }

    @Override
    public boolean validateRoomHardness(int hardness) {
        return hardness == 0 || hardness == 1;
    }

    private boolean validateN(int number) {
        return (number > 3) && (number <= 500);
    }

    private boolean validateK(int number) {
        return number >= 3;
    }

}
