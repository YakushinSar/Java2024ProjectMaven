package lesson12_1;

public class Sid {

    public static String howMutchLoveYou(int nb_petals){
        String[] arr = {"not at all", "I love you", "a little", "a lot", "passionately", "nadlu"};
        return  arr[nb_petals%6];
    }

    public static void main(String[] args) {
        System.out.println(howMutchLoveYou(6));
    }
}
