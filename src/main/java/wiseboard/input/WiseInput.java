package wiseboard.input;

import java.util.Scanner;
import wiseboard.domain.WiseQuote;
import wiseboard.repository.WiseRepository;
import wiseboard.service.WiseService;
import wiseboard.view.WiseOutput;

public class WiseInput {

    private static final String Finish_Command = "종료";
    private static final String Register_Command = "등록";
    private static final String List_Command = "목록";
    private static final String DELETE_PREFIX = "삭제?id=";
    private static final String MODIFY_PREFIX = "수정?id=";

    private final Scanner scanner;
    private final WiseOutput wiseOutput;
    private final WiseService wiseService;

    public WiseInput(WiseOutput wiseOutput) {
        this.wiseOutput = wiseOutput;
        this.scanner = new Scanner(System.in);

        WiseRepository wiseRepository = new WiseRepository();
        this.wiseService = new WiseService(wiseRepository);
    }

    public void Start() {
        wiseOutput.AppTitle();

        while (true) {
            String command = Input();

            switch (command) {
                case Finish_Command:
                    return;
                case Register_Command:
                    Register();
                    continue;
                case List_Command:
                    List();
                    continue;
            }

            if (command.startsWith(DELETE_PREFIX)) {
                Delete(command);
                continue;
            }

            if (command.startsWith(MODIFY_PREFIX)) {
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

        Integer id = wiseService.Register(author, content);
        wiseOutput.Registered(id);
    }

    private void List() {
        wiseOutput.ListHeader();

        WiseQuote[] quotes = wiseService.FindAllDesc();
        wiseOutput.ListRows(quotes);
    }

    private void Delete(String command) {
        Integer id = ExtractId(command, DELETE_PREFIX);

        boolean deleted = wiseService.Delete(id);

        if (deleted) {
            wiseOutput.Deleted(id);
            return;
        }

        wiseOutput.NotFound(id);
    }

    private void Modify(String command) {
        Integer id = ExtractId(command, MODIFY_PREFIX);

        WiseQuote quote = wiseService.FindById(id);

        if  (quote == null) {
            wiseOutput.NotFound(id);
            return;
        }

        wiseOutput.ModifyExistingContent(quote.content());
        wiseOutput.QuotePrompt();
        String newContent = scanner.nextLine();

        wiseOutput.ModifyExistingAuthor(quote.author());
        wiseOutput.AuthorPrompt();
        String newAuthor = scanner.nextLine();

        wiseService.Modify(id, newAuthor, newContent);
    }

    private Integer ExtractId(String command, String prefix) {
        String value = command.substring(prefix.length()).trim();
        return Integer.valueOf(value);
    }
}