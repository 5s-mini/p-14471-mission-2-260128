package wiseboard.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import wiseboard.domain.WiseQuote;

public class WiseRepository {

    private final List<WiseQuote> quotes;
    private Integer nextId;

    public WiseRepository() {
        this.quotes = new ArrayList<>();
        this.nextId = 1;
    }

    public WiseQuote Save(String author, String content) {
        Integer id = nextId;
        WiseQuote wiseQuote = new WiseQuote(id, author, content);

        quotes.add(wiseQuote);
        nextId++;
        return wiseQuote;
    }

    public WiseQuote FindById(Integer id) {
        for (WiseQuote quote : quotes) {
            if (quote.id().equals(id)) {
                return quote;
            }
        }

        return null;
    }

    public boolean DeleteById(Integer id) {
        Integer index = FindIndexById(id);

        if (index == null) {
            return false;
        }

        quotes.remove(index.intValue());
        return true;
    }

    public boolean ReplaceById(Integer id, String author, String content) {
        Integer index = FindIndexById(id);

        if (index == null) {
            return false;
        }

        quotes.set(index, new WiseQuote(id, author, content));
        return true;
    }

    public List<WiseQuote> FindAllDesc() {
        List<WiseQuote> wiseCopy = new ArrayList<>(quotes);
        wiseCopy.sort(Comparator.comparing(WiseQuote::id).reversed());
        return wiseCopy;
    }

    private Integer FindIndexById(Integer id) {
        int size = quotes.size();
        int i = 0;

        while (i < size) {
            WiseQuote wiseQuote = quotes.get(i);

            if (wiseQuote.id().equals(id)) {
                return i;
            }

            i++;
        }

        return null;
    }
}