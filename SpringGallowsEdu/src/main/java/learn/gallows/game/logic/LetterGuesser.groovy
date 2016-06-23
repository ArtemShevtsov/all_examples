package learn.gallows.game.logic

import learn.gallows.game.exception.TooMuchTriesUsedException

/**
 * Created by Artem_Shevtsov on 5/27/2016.
 */
class LetterGuesser {
    private String sourceWord
    private String sourceWordInCurrentState
    private int guessCount

    public Optional<String> getNull(){
        return null
    }

    public static void main(String[] args) {
        LetterGuesser lg = new LetterGuesser('ewer')
        Optional<String> optNull = lg.getNull()
optNull.isPresent()
    }


    LetterGuesser(String sourceWord) {
        this.sourceWord = sourceWord
        sourceWordInCurrentState = this.sourceWord
        guessCount = 3
    }

    String getSourceWord() {
        return sourceWord
    }

    String getSourceWordInCurrentState() {
        return sourceWordInCurrentState
    }

    int getGuessCount() {
        return guessCount
    }

    void setGuessCount(int guessCount) {
        this.guessCount = guessCount
    }

    public boolean isCharacterExists(char character){
        return sourceWordInCurrentState.contains(character as String)
    }

    public  GameStatus doGuess(char character){
        boolean isExists = isCharacterExists(character)
        if(isExists){
            sourceWordInCurrentState = sourceWordInCurrentState - character
            if(sourceWordInCurrentState.length() == 0){
                return GameStatus.WON
            }
        } else {
            if(this.guessCount == 0){
                return GameStatus.FAILED
            }
            setGuessCount(this.guessCount - 1)
            return GameStatus.WRONG_GUESS
        }

        return GameStatus.CONTINUE
    }
}
