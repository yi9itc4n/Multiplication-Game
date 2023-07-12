public class Question1 {
    private int rand1;
    private int rand2;

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    private int answer;

    @Override
    public String toString() {
        if(answer==1) {
            return "Question: " +
                    rand1 + '*' +
                    rand2 +
                    "   Seconds elapsed=" + secondsElapsed +
                    "\nAnswer=" + "true" +
                    "\n";
        }
        else {
            return "Question: " +
                    rand1 + '*' +
                    rand2 +
                    "\nSeconds elapsed=" + secondsElapsed +
                    "\nAnswer=" + "false" +
                    "\n";
        }
    }
    public String toReport(){
        StringBuilder sb = new StringBuilder();
        if(answer==1)
            sb.append(rand1).append("*").append(rand2).append("\t").append(secondsElapsed).append("\t").append("TRUE").append("\t");
        else
            sb.append(rand1).append("*").append(rand2).append("\t").append(secondsElapsed).append("\t").append("FALSE").append("\t");

        return sb.toString();
    }

    private int secondsElapsed;
    private static int correct;

    public static void setCorrect(int correct) {
        Question1.correct = correct;
    }

    public int getRand1() {
        return rand1;
    }

    public void setRand1(int rand1) {
        this.rand1 = rand1;
    }

    public int getRand2() {
        return rand2;
    }

    public void setRand2(int rand2) {
        this.rand2 = rand2;
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public void setSecondsElapsed(int secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public static int getCorrect() {
        return correct;
    }



    public String generateRandomNumbers(int min, int max) {
        rand1 = (int) (Math.random() * (max - min + 1) + min);
        rand2 = (int) (Math.random() * (max - min + 1) + min);
        String s = getQuestionString(rand1, rand2);
        return s;
    }
    public String getQuestionString(int rand1, int rand2) {
        return rand1 + " * " + rand2 + " = ";
    }
    public int getAnswer() {
        return answer;
    }

}
