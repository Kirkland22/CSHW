import java.util.ArrayList;

/**
 * Created by Kirkland on 10/21/17.
 */
public class MultipleChoice extends Question {



    public MultipleChoice() {
        setQuestionType("Multiple Choice");
    }

    // Gets the Correct Answers for MC
    @Override
    public void setAnswer() {

        String input = null;
        ChoiceResponse<String> answers = null;
        ArrayList<String> multipleChoices = new ArrayList<>();
        display();
        consoleOutput.display("Enter Number of Correct Answers:");

        try {


            int inputNum = Integer.parseInt(consoleInput.getInput());

            if(inputNum > getNumOfChoices())
                throw new IllegalArgumentException();

            setNumOfCorrectAnswers(inputNum);

            // Gets Multiple choice options for given question -- Returns A -> D if question has 4 options
            for (int i = 0; i < getNumOfChoices(); i++) {

                multipleChoices.add(getMultipleChoiceOptions().get(i));

            }

            // Get Correct Answers
            for (int i = 0; i < getNumOfCorrectAnswers() ; i++) {
                boolean isNotValidAnswer = true;
                answers = new StringChoiceResponse();
                consoleOutput.display("Enter Answer #" + (i + 1) + ":");
                input = consoleInput.getInput().toUpperCase();

                    if (!multipleChoices.contains(input))
                        throw new IllegalStateException();

                answers.setResponse(input);
                addAnswer(answers);
                }



            }


        catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            setAnswer();
        }
        catch (IllegalArgumentException e) {
            consoleOutput.display("Can Not Have More Correct Answers Than Choices");
            setAnswer();

        }

    }

    @Override
    public void display() {

        ArrayList<ChoiceResponse> leftSideChoices = getQuestionChoices();

        getPrompt().display();
        for (int i = 0; i < leftSideChoices.size(); i++) {

            consoleOutput.displayOneLine(getMultipleChoiceOptions().get(i) + ") " + leftSideChoices.get(i).getResponse() + "\n");
        }

        consoleOutput.displayOneLine("\n");
    }
}


