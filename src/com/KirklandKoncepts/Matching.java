package com.KirklandKoncepts;

import java.util.ArrayList;

/**
 * Created by Kirkland on 10/28/17.
 */
public class Matching extends Question {

    private ArrayList<ChoiceResponse> rightSideChoices = new ArrayList<>();

    public Matching() {
        setQuestionType("Matching");
    }


    @Override
    public void create() {

    getPromptFromUser();
    getChoices();

    }


    private void getChoices() {

        ChoiceResponse<String> choice_Column1;
        ChoiceResponse<String> choice_Column2;
        // Get Number of options
        try {
            consoleOutput.display("How many " + getQuestionType() + " choices would you like?");
            setNumOfChoices(Integer.parseInt(consoleInput.getInput()));

            if (getNumOfChoices() < 2)
                throw new IllegalArgumentException();

            // Gets Choices for the Left Side of the Matching Questions
            for (int i = 0; i < getNumOfChoices(); i++) {
                choice_Column1 = new StringChoiceResponse();
                consoleOutput.display("(Left Column) Enter Choice #"+ (i + 1) + ":");
                choice_Column1.setResponse(consoleInput.getInput());
                addAnswer(choice_Column1);
            }

            // Gets Choices for the Right Side of the Matching Questions
            for (int i = 0; i < getNumOfChoices(); i++) {
                choice_Column2 = new StringChoiceResponse();
                consoleOutput.display("(Right Column) Enter Choice #"+ (i + 1) + ":");
                choice_Column2.setResponse(consoleInput.getInput());
                addSecondColumn(choice_Column2);
            }


        } catch (NumberFormatException e) {
            consoleOutput.display("Enter a number");
            getChoices();
        } catch (IllegalArgumentException e) {
            consoleOutput.display("Must have more than one choice");
            getChoices();

        } catch (Exception e) {
            e.printStackTrace();
            getChoices();

        }
    }

    @Override
    public void getAnswer() {

    }

    @Override
    public void display() {

        getPrompt().display();
        ArrayList<ChoiceResponse> leftSideChoices = getChoiceResponses();

        for (int i = 0; i < leftSideChoices.size(); i++) {

            consoleOutput.displayONELINE(getMutlipleChoiceOptions()[i] + ") " + leftSideChoices.get(i).getResponse() + "\t" + (i+1) + ") " + rightSideChoices.get(i).getResponse() + "\n" );
        }

        consoleOutput.displayONELINE("\n");

    }


    private void addSecondColumn(ChoiceResponse choiceResponse) {
        rightSideChoices.add(choiceResponse);
    }


}