package wiseboard.view;

import wiseboard.domain.WiseQuote;

public class WiseOutput {

    private static final String EMPTY_LIST_OUTPUT = "등록된 명언이 없습니다.";

    public void AppTitle() {
        System.out.println("== 명언 앱 ==");
    }

    public void CommandPrompt() {
        System.out.print("명령) ");
    }

    public void QuotePrompt() {
        System.out.print("명언 : ");
    }

    public void AuthorPrompt() {
        System.out.print("작가 : ");
    }

    public void ListHeader() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
    }

    public void Registered(Integer id) {
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void Deleted(Integer id) {
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void NotFound(Integer id) {
        System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void ModifyExistingContent(String content) {
        System.out.println("명언(기존) : " + content);
    }

    public void ModifyExistingAuthor(String author) {
        System.out.println("작가(기존) : " + author);
    }

    public void ListRows(WiseQuote[] quotes) {
        int i = 0;
        int n = quotes.length;

        while (i < n) {
            WiseQuote quote = quotes[i];
            System.out.println(quote.id() + " / " + quote.author() + " / " + quote.content());
            i++;
        }
    }

    public void EmptyList() {
        System.out.println(EMPTY_LIST_OUTPUT);
    }
}