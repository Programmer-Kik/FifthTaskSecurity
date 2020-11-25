import java.util.*;

public class WeinersEncoding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ключевое слово: ");
        String key = in.nextLine().toLowerCase();

        System.out.print("Введите текст для шифрования: ");
        String[] words = in.nextLine().toLowerCase().split(" ");
        StringBuilder text = new StringBuilder();
        for (String word:words) {
            text.append(word);
        }
        String preparedText = text.toString();

        char[] alphabet = new char[] {'а', 'б', 'в', 'г', 'д', 'е', 'ё',
                'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
                'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
                'ь', 'э', 'ю', 'я'};

        char[][] matrix = new char[alphabet.length][alphabet.length];
        for (int i = 0, k; i < matrix.length; i++) {
            k = i;
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = alphabet[k];
                k++;
                if (k == alphabet.length) {
                    k = 0;
                }
            }
        }

        String preparedKey = key.repeat(preparedText.length() / key.length() + 1)
                .substring(0, preparedText.length());

        List<String> encodedText = new ArrayList<>();
        for (int i = 0, j = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            char[] partKey = preparedKey.substring(j, j + word.length).toCharArray();

            StringBuilder encodedWord = new StringBuilder();
            for (int k = 0; k < word.length; k++) {
                encodedWord.append(matrix[Arrays.binarySearch(alphabet, partKey[k])]
                        [Arrays.binarySearch(alphabet, word[k])]);
                j++;
            }
            encodedText.add(encodedWord.toString());
        }

        System.out.print("Закодированный текст: ");
        for (String word:encodedText) {
            System.out.print(word + " ");
        }
    }
}
