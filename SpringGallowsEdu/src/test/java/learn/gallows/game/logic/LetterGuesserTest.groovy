package learn.gallows.game.logic

import learn.gallows.game.exception.TooMuchTriesUsedException
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Artem_Shevtsov on 5/27/2016.
 */

class LetterGuesserTest {
    private LetterGuesser guesser;

    @Before
    public void prepare(){
        guesser = new LetterGuesser("папоротник")
    }

    @Test
    public void shouldReturnTrueIfCharacterExistsAtFirstTime() throws Exception {
        Assert.assertEquals(true, guesser.isCharacterExists('п' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('а' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('о' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('р' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('т' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('н' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('и' as char))
        Assert.assertEquals(true, guesser.isCharacterExists('к' as char))
    }

    @Test
    public void shouldReturnFalseIfCharacterDoesNoExists() throws Exception {
        Assert.assertEquals(false, guesser.isCharacterExists('l' as char))
    }

    @Test
    public void shouldDecreaseGuessesCountAfterWrongGuess() throws Exception {
        guesser.doGuess('e' as char)
        Assert.assertEquals(2, guesser.getGuessCount())

        Assert.assertEquals("папоротник", guesser.getSourceWord())
        Assert.assertEquals("папоротник", guesser.getSourceWordInCurrentState())
    }

    @Test
    public void shouldExtractGuesedChatacter() throws Exception {
        guesser.doGuess('о' as char)

        Assert.assertEquals(10, guesser.getSourceWord().length())
        Assert.assertEquals(9, guesser.getSourceWordInCurrentState().length())
        Assert.assertEquals("папоротник", guesser.getSourceWord())
        Assert.assertEquals("папротник", guesser.getSourceWordInCurrentState())
    }

    @Test
    public void shouldReturnFailedStatusWhenTooManyGuessesWasUsed() throws Exception {
        guesser.doGuess('q' as char)
        guesser.doGuess('w' as char)
        guesser.doGuess('e' as char)

        Assert.assertEquals(GameStatus.FAILED, guesser.doGuess('r' as char))
    }

    @Test
    public void shouldReturnWrongGuessStatusWhenCharacterWasNotGuessed() throws Exception {
        Assert.assertEquals(GameStatus.WRONG_GUESS, guesser.doGuess('r' as char))
    }

    @Test
    public void shouldReturnContinueStatusWhenCharacterGuessed() throws Exception {
        Assert.assertEquals(GameStatus.CONTINUE, guesser.doGuess('р' as char))
    }

    @Test
    public void shouldReturnWonStatusWhenWordWasGuessed() throws Exception {
        guesser.doGuess('п' as char)
        guesser.doGuess('а' as char)
        guesser.doGuess('п' as char)
        guesser.doGuess('о' as char)
        guesser.doGuess('р' as char)
        guesser.doGuess('о' as char)
        guesser.doGuess('т' as char)
        guesser.doGuess('н' as char)
        guesser.doGuess('и' as char)
        Assert.assertEquals(GameStatus.WON, guesser.doGuess('к' as char))
    }
}
