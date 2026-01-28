package wiseboard.input;

import java.util.Scanner;
import wiseboard.view.WiseOutput;

public class WiseInput {

    private final Scanner scanner;
    private final WiseOutput wiseOutput;

    public WiseInput(WiseOutput wiseOutput) {
        this.wiseOutput = wiseOutput;
        this.scanner = new Scanner(System.in);
    }

    public void Start() {
        wiseOutput.AppTitle();

        while (true) {
            String command = Input();

            switch (command) {
                case "종료":
                    return;
                case "등록":
                    Register();
                    continue;
                case "목록":
                    List();
                    continue;
            }

            if (command.startsWith("삭제?id=")) {
                Delete(command);
                continue;
            }

            if (command.startsWith("수정?id=")) {
                Modify(command);
                continue;
            }

            throw new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요.");
        }
    }

    public String Input() {
        wiseOutput.CommandPrompt();
        return scanner.nextLine().trim();
    }

    private void Register() {
        wiseOutput.QuotePrompt();
        String content = scanner.nextLine();

        wiseOutput.AuthorPrompt();
        String author = scanner.nextLine();
    }

    private void List() {

    }

    private void Delete(String command) {

    }

    private void Modify(String command) {

    }
}