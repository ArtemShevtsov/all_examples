package learn.gallows.game.exception

/**
 * Created by Artem_Shevtsov on 5/27/2016.
 */
class TooMuchTriesUsedException extends Exception {
    @Override
    String getMessage() {
        return "Too much tries was used"
    }
}
